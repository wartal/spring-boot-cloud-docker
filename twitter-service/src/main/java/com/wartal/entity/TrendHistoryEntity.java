package com.wartal.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.wartal.View;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by lwartalski on 11/10/16.
 */
@Entity
public class TrendHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.Basic.class)
    private Long id;
    @JsonView(View.Basic.class)
    private Date createdDate;
    @JsonView(View.Basic.class)
    private Integer woeid;

    @JsonView(View.Basic.class)
    private String country;
    @JsonView(View.Basic.class)
    private String city;
    @JsonView(View.Detail.class)
    @OneToMany(mappedBy = "trendHistory", cascade = CascadeType.ALL)
    private List<TrendEntity> trendEntities;

    public TrendHistoryEntity() {
    }

    public TrendHistoryEntity(String country, String city) {
        this.country = country;
        this.city = city;
    }

    @PrePersist
    private void prePersist() {
        createdDate = new Date();
    }

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

    public List<TrendEntity> getTrendEntities() {
        return trendEntities;
    }

    public void setTrendEntities(List<TrendEntity> trendEntities) {
        trendEntities.forEach(e -> e.setTrendHistory(this));
        this.trendEntities = trendEntities;
    }
}
