package com.wartal.twitterapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by lwartalski on 10/10/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrendLocation extends RestResponse {
    private Integer woeid;
    private String country;
    private String name;

    public TrendLocation() {
    }

    public TrendLocation(Integer woeid, String country, String name) {
        this.woeid = woeid;
        this.country = country;
        this.name = name;
    }

    public Integer getWoeid() {
        return woeid;
    }

    public void setWoeid(Integer woeid) {
        this.woeid = woeid;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
