package com.wartal.twitter;

import java.util.Date;
import java.util.List;

/**
 * Created by lwartalski on 11/10/16.
 */
public class TrendHistory {

    private Long id;
    private Date createdDate;
    private Integer woeid;
    private String country;
    private String city;
    private List<Trend> trendEntities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Trend> getTrendEntities() {
        return trendEntities;
    }

    public void setTrendEntities(List<Trend> trendEntities) {
        this.trendEntities = trendEntities;
    }
}
