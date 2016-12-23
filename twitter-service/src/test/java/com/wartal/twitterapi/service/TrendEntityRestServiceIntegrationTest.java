package com.wartal.twitterapi.service;

import com.wartal.TestConfiguration;
import com.wartal.twitterapi.Trend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by lwartalski on 11/10/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AuthenticationService.class, TestConfiguration.class, TrendRestService.class})
public class TrendEntityRestServiceIntegrationTest {

    @Autowired
    private TrendRestService trendRestService;

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionIfInputParameterIsNullTest() {
        trendRestService.validateInputParam(null);
    }

    @Test
    public void shouldGetTrendsTest() {
        final List<Trend> trends = trendRestService.invoke(1);
        assertNotNull(trends);
        assertTrue(trends.size() > 0);
        final Trend trend = trends.get(0);
        assertNotNull(trend.getName());
        assertNotNull(trend.getQuery());
        assertNotNull(trend.getUrl());
    }
}