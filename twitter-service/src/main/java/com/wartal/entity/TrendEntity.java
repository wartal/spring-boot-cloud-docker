package com.wartal.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.wartal.View;

import javax.persistence.*;

/**
 * Created by lwartalski on 11/10/16.
 */
@Entity
public class TrendEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonView(View.Basic.class)
    private String name;
    @JsonView(View.Basic.class)
    private String query;
    @JsonView(View.Basic.class)
    private Integer tweetVolume;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private TrendHistoryEntity trendHistory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public TrendHistoryEntity getTrendHistory() {
        return trendHistory;
    }

    public void setTrendHistory(TrendHistoryEntity trendHistory) {
        this.trendHistory = trendHistory;
    }
}
