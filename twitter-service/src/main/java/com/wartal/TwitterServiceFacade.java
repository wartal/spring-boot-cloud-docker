package com.wartal;

import com.fasterxml.jackson.annotation.JsonView;
import com.wartal.entity.TrendHistoryEntity;
import com.wartal.twitterapi.Trend;
import com.wartal.twitterapi.TrendLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by lwartalski on 11/10/16.
 */
@RestController
public class TwitterServiceFacade {

    @Autowired
    private TwitterService twitterService;

    @RequestMapping(value = "/history", method = GET)
    @JsonView(value = View.Basic.class)
    public List<TrendHistoryEntity> findAll() {
        return twitterService.findAllHistoryData();
    }

    @RequestMapping(value = "/history/{trendHistoryId}", method = GET)
    @JsonView(value = View.Detail.class)
    public TrendHistoryEntity findOne(@PathVariable Long trendHistoryId) {
        return twitterService.findHistoryById(trendHistoryId);
    }

    @RequestMapping(value = "/search/{woeid}", method = GET)
    @JsonView(value = View.Detail.class)
    public TrendHistoryEntity search(@PathVariable Integer woeid) {
        final List<Trend> trends = twitterService.findTrends(woeid);
        return twitterService.saveTrends(trends, woeid);
    }

    @RequestMapping(value = "/locations", method = GET)
    public List<TrendLocation> findLocations() {
        return twitterService.findLocations();
    }
}
