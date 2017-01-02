package com.wartal.twitterapi.service;

import com.wartal.TestConfiguration;
import com.wartal.twitterapi.TrendLocation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by lwartalski on 10/10/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AuthenticationService.class, TestConfiguration.class, LocationRestService.class})
public class LocationRestServiceIntegrationTest {

    @Autowired
    private LocationRestService locationRestService;

    @Test
    public void shouldGetAvailableLocationsTest() {
        final List<TrendLocation> trendLocations = locationRestService.invoke();
        assertNotNull(trendLocations);
        assertTrue(trendLocations.size() > 0);
    }
}