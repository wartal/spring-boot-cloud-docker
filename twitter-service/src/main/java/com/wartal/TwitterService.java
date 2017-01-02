package com.wartal;

import com.wartal.entity.TrendHistoryEntity;
import com.wartal.twitterapi.Trend;
import com.wartal.twitterapi.TrendLocation;

import java.util.List;

/**
 * Created by lwartalski on 11/10/16.
 */
public interface TwitterService {

    /**
     * Find all history data
     *
     * @return
     */
    List<TrendHistoryEntity> findAllHistoryData();

    /**
     * Find history entity by id
     *
     * @param trendHistoryId
     * @return
     */
    TrendHistoryEntity findHistoryById(Long trendHistoryId);

    /**
     * Find trend by woeid
     *
     * @param woeid <a href="https://dev.twitter.com/rest/reference/get/trends/place">woeid</a>
     * @return
     */
    List<Trend> findTrends(Integer woeid);

    /**
     * Save trends
     *
     * @param trends trends list
     * @param woeid  <a href="https://dev.twitter.com/rest/reference/get/trends/place">woeid</a>
     * @return
     */
    TrendHistoryEntity saveTrends(List<Trend> trends, Integer woeid);

    /**
     * Find all available locations
     *
     * @return
     */
    List<TrendLocation> findLocations();
}
