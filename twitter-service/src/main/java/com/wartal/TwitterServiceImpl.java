package com.wartal;

import com.wartal.entity.TrendEntity;
import com.wartal.entity.TrendHistoryEntity;
import com.wartal.twitterapi.Trend;
import com.wartal.twitterapi.TrendLocation;
import com.wartal.twitterapi.service.LocationRestService;
import com.wartal.twitterapi.service.TrendRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lwartalski on 11/10/16.
 */
@Service
public class TwitterServiceImpl implements TwitterService {

    @Autowired
    private TrendHistoryRepository trendHistoryRepository;

    @Autowired
    private LocationRestService locationRestService;

    @Autowired
    private TrendRestService trendRestService;

    @Autowired
    private TrendMapper trendMapper;

    @Override
    public List<TrendHistoryEntity> findAllHistoryData() {
        return trendHistoryRepository.findAll(new Sort(Sort.Direction.DESC, "id"));
    }

    @Override
    @Transactional(readOnly = true)
    public TrendHistoryEntity findHistoryById(Long trendHistoryId) {
        return trendHistoryRepository.findOne(trendHistoryId);
    }

    @Override
    @Transactional
    public TrendHistoryEntity findAndSaveTrend(Integer woeid) {
        final List<Trend> trends = trendRestService.invoke(woeid);

        TrendHistoryEntity trendHistoryEntity = initTrendHistoryEntity(woeid);
        final List<TrendEntity> entities = trends.stream()
                .map(trendMapper::map)
                .collect(Collectors.toList());

        trendHistoryEntity.setTrendEntities(entities);
        return trendHistoryRepository.save(trendHistoryEntity);
    }

    @Override
    public List<TrendLocation> findLocations() {
        return locationRestService.invoke();
    }

    private TrendHistoryEntity initTrendHistoryEntity(Integer woeid) {
        TrendHistoryEntity trendHistoryEntity = new TrendHistoryEntity();
        trendHistoryEntity.setWoeid(woeid);

        final TrendLocation trendLocation = findLocations().stream()
                .filter(l -> l.getWoeid().equals(woeid))
                .findFirst()
                .get();

        trendHistoryEntity.setCity(trendLocation.getName());
        trendHistoryEntity.setCountry(trendLocation.getCountry());
        return trendHistoryEntity;
    }
}
