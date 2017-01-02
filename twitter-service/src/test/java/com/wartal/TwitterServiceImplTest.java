package com.wartal;

import com.wartal.entity.TrendHistoryEntity;
import com.wartal.twitterapi.Trend;
import com.wartal.twitterapi.TrendLocation;
import com.wartal.twitterapi.service.LocationRestService;
import com.wartal.twitterapi.service.TrendRestService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
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

    @Spy
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
    public void shouldFindTrendsTest() {
        int woeid = 1;
        final Trend trend = new Trend();
        trend.setName("HappyNewYear2017");
        given(trendRestService.invoke(woeid)).willReturn(Stream.of(trend).collect(Collectors.toList()));
        final List<Trend> trends = twitterService.findTrends(woeid);

        then(trendRestService).should().invoke(woeid);

        assertThat(trends).isNotEmpty();
        assertThat(trends.get(0).getName()).isEqualTo("HappyNewYear2017");
    }

    @Test
    public void shouldSaveTrendsTest() {
        int woeid = 1;
        final String country = "Poland";
        final String city = "Warsaw";

        final Trend trend = new Trend();
        trend.setName("HappyNewYear2017");
        final List<Trend> trends = Stream.of(trend).collect(Collectors.toList());

        given(locationRestService.invoke())
                .willReturn(Stream.of(new TrendLocation(1, country, city)).collect(Collectors.toList()));

        given(repository.save(any(TrendHistoryEntity.class)))
                .willAnswer(invocation -> invocation.getArgumentAt(0, TrendHistoryEntity.class));

        final TrendHistoryEntity result = twitterService.saveTrends(trends, woeid);

        then(locationRestService).should().invoke();
        then(trendMapper).should().map(trend);
        then(repository).should().save(any(TrendHistoryEntity.class));

        assertThat(result.getWoeid()).isEqualTo(1);
        assertThat(result.getCity()).isEqualTo("Warsaw");
        assertThat(result.getCountry()).isEqualTo("Poland");
        assertThat(result.getTrendEntities()).isNotEmpty();
        assertThat(result.getTrendEntities().get(0).getName()).isEqualTo("HappyNewYear2017");

    }


    @Test
    public void shouldFindLocationsTest() throws Exception {
        given(locationRestService.invoke()).willReturn(
                Stream.of(new TrendLocation(1, "Poland", "Warsaw")).collect(Collectors.toList())
        );

        final List<TrendLocation> locations = twitterService.findLocations();

        verify(locationRestService).invoke();
        assertNotNull(locations);
        assertTrue(locations.size() == 1);
        assertEquals(Integer.valueOf(1), locations.get(0).getWoeid());
        assertEquals("Poland", locations.get(0).getCountry());
        assertEquals("Warsaw", locations.get(0).getName());
    }
}