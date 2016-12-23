package com.wartal.twitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by lwartalski on 11/10/16.
 */
@RestController
@RequestMapping("/twitter")
public class TwitterApiController {

    @Value("${twitter.service.host}")
    private String host;

    @Value("${twitter.service.port}")
    private String port;

    @Value("${message}")
    private String message;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/message", method = GET)
    public Message getMessage() {
        return new Message(message);
    }

    @RequestMapping(value = "/history", method = GET)
    public List<TrendHistory> getAllTrendHistoryData() {
        final String url = "http://{host}:{port}/history";
        final ResponseEntity<List<TrendHistory>> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<TrendHistory>>() {
                },
                host, port);

        return response.getBody();
    }

    @RequestMapping(value = "/history/{trendHistoryId}", method = GET)
    public TrendHistory getTrendHistoryById(@PathVariable Long trendHistoryId) {
        final String url = "http://{host}:{port}/history/{trendHistoryId}";
        final ResponseEntity<TrendHistory> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<TrendHistory>() {
                },
                host, port, trendHistoryId);

        return response.getBody();
    }

    @RequestMapping(value = "/locations", method = GET)
    public List<TrendLocation> getLocations() {
        final String url = "http://{host}:{port}/locations";
        final ResponseEntity<List<TrendLocation>> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<TrendLocation>>() {
                },
                host, port);

        return response.getBody();
    }

    @RequestMapping(value = "/search/{woeid}", method = GET)
    public TrendHistory search(@PathVariable Long woeid) {
        final String url = "http://{host}:{port}/search/{woeid}";
        final ResponseEntity<TrendHistory> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<TrendHistory>() {
                },
                host, port, woeid);

        return response.getBody();
    }
}
