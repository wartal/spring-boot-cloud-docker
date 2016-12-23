package com.wartal.twitterapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lwartalski on 11/10/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrendWrapper extends RestResponse {

    @JsonProperty("trends")
    private List<Trend> trends = new ArrayList<>();

    public List<Trend> getTrends() {
        return trends;
    }

    public void setTrends(List<Trend> trends) {
        this.trends = trends;
    }
}
