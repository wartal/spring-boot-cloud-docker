package com.wartal;

import com.wartal.entity.TrendEntity;
import com.wartal.twitterapi.Trend;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by lwartalski on 11/10/16.
 */
public class TrendMapperTest {

    TrendMapper mapper = new TrendMapper();

    @Test
    public void shouldMapDtoToEntity() {
        Trend trend = new Trend();
        trend.setName("name");
        trend.setQuery("query");
        trend.setTweetVolume(1);
        final TrendEntity entity = mapper.map(trend);
        assertNotNull(entity);
        assertEquals("name", entity.getName());
        assertEquals("query", entity.getQuery());
        assertEquals(Integer.valueOf(1), entity.getTweetVolume());
    }
}