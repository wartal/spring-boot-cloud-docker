package com.wartal;

import com.wartal.entity.TrendEntity;
import com.wartal.twitterapi.Trend;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by lwartalski on 11/10/16.
 */
public class TrendMapperTest {

    TrendMapper mapper = new TrendMapper();

    @Test
    public void whenMapThenReturnTrendName() {
        final String name = "chuck norris";
        Trend trend = new Trend();
        trend.setName(name);
        final TrendEntity entity = mapper.map(trend);
        assertEquals("Trend name value is not correct", "chuck norris", entity.getName());
    }

    @Test
    public void whenMapThenReturnTrendQuery() {
        final String query = "chuck norris";
        Trend trend = new Trend();
        trend.setQuery(query);

        final TrendEntity entity = mapper.map(trend);
        assertEquals("Trend query value is not correct", "chuck norris", entity.getQuery());
    }

    @Test
    public void whenMapThenReturnTrendTweetVolume() {
        final Integer tweetVolume = 1;
        Trend trend = new Trend();
        trend.setTweetVolume(tweetVolume);

        final TrendEntity entity = mapper.map(trend);
        assertEquals("Trend tweet volume is not correct", Integer.valueOf(1), trend.getTweetVolume());

    }
}