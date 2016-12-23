package com.wartal.twitter;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by lwartalski on 10/10/16.
 */
public class Trend {
    private String name;
    private String query;
    private Integer tweetVolume;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Integer getTweetVolume() {
        return tweetVolume;
    }

    public void setTweetVolume(Integer tweetVolume) {
        this.tweetVolume = tweetVolume;
    }
}