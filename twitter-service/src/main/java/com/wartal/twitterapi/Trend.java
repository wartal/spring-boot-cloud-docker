package com.wartal.twitterapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Twitter trends / place
 *
 * @see <a href="https://dev.twitter.com/rest/reference/get/trends/place">trends/place</a>
 * Created by lwartalski on 10/10/16.
 */
public class Trend {
    private String name;
    private String url;
    private String query;
    @JsonProperty(value = "promoted_content")
    private String promotedContent;
    @JsonProperty(value = "tweet_volume")
    private Integer tweetVolume;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.DEFAULT_STYLE);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getPromotedContent() {
        return promotedContent;
    }

    public void setPromotedContent(String promotedContent) {
        this.promotedContent = promotedContent;
    }

    public Integer getTweetVolume() {
        return tweetVolume;
    }

    public void setTweetVolume(Integer tweetVolume) {
        this.tweetVolume = tweetVolume;
    }
}