package com.wartal;

import com.wartal.entity.TrendHistoryEntity;
import com.wartal.twitterapi.Trend;
import com.wartal.twitterapi.TrendLocation;
import com.wartal.twitterapi.service.LocationRestService;
import com.wartal.twitterapi.service.TrendRestService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * Created by lwartalski on 11/10/16.
 */
public class TwitterServiceImplTest {

    @InjectMocks
    private TwitterServiceImpl twitterService;

    @Mock
    private TrendHistoryRepository repository;

    @Mock
    private TrendRestService trendRestService;

    @Mock
    private LocationRestService locationRestService;

    @Mock
    private TrendMapper trendMapper;

    @Captor
    private ArgumentCaptor<TrendHistoryEntity> historyEntityArgumentCaptor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldFindAllHistoryDataTest() throws Exception {
        twitterService.findAllHistoryData();
        verify(repository).findAll(new Sort(Sort.Direction.DESC, "id"));
    }

    @Test
    public void shouldFindHistoryByIdTest() throws Exception {
        twitterService.findHistoryById(1L);
        verify(repository).findOne(1L);
    }

    @Test
    public void shouldFindAndSaveTrendTest() throws Exception {
        final int woeid = 1;
        final Trend trend = new Trend();
        trend.setName("MyTrend");

        given(trendRestService.invoke(woeid)).willReturn(Stream.of(trend).collect(Collectors.toList()));
        given(locationRestService.invoke()).willReturn(
                Stream.of(new TrendLocation(1, "Poland", "Warsaw")).collect(Collectors.toList())
        );
        given(trendMapper.map(Mockito.any())).willCallRealMethod();

        twitterService.findAndSaveTrend(woeid);

        verify(trendRestService).invoke(woeid);
        verify(locationRestService).invoke();
        verify(trendMapper).map(trend);
        verify(repository).save(historyEntityArgumentCaptor.capture());

        final TrendHistoryEntity trendHistoryEntity = historyEntityArgumentCaptor.getValue();
        Assert.assertEquals(Integer.valueOf(1), trendHistoryEntity.getWoeid());
        Assert.assertEquals("Poland", trendHistoryEntity.getCountry());
        Assert.assertEquals("Warsaw", trendHistoryEntity.getCity());
        Assert.assertNotNull(trendHistoryEntity.getTrendEntities());
        Assert.assertEquals("MyTrend", trendHistoryEntity.getTrendEntities().get(0).getName());
    }

    @Test
    public void shouldFindLocationsTest() throws Exception {
        given(locationRestService.invoke()).willReturn(
                Stream.of(new TrendLocation(1, "Poland", "Warsaw")).collect(Collectors.toList())
        );

        final List<TrendLocation> locations = twitterService.findLocations();

        verify(locationRestService).invoke();
        Assert.assertNotNull(locations);
        Assert.assertTrue(locations.size() == 1);
        Assert.assertEquals(Integer.valueOf(1), locations.get(0).getWoeid());
        Assert.assertEquals("Poland", locations.get(0).getCountry());
        Assert.assertEquals("Warsaw", locations.get(0).getName());
    }
}