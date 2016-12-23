package com.wartal.twitterapi.service;

import com.wartal.twitterapi.Token;
import com.wartal.twitterapi.TrendLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * GET trends/available
 * <p>
 * Created by lwartalski on 10/10/16.
 */
@Service
public class LocationRestService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Value("${twitter.endpoint.trend.available}")
    private String endpoint;

    @Value("${twitter.api.url}")
    private String twitterUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AuthenticationService authenticationService;

    /**
     * Get list of available locations
     * @return List
     */
    @Cacheable("locations")
    public List<TrendLocation> invoke() {
        final Token token = authenticationService.getToken();
        log.info("Get locations. Token={}", token);

        final ResponseEntity<List<TrendLocation>> response = restTemplate.exchange(
                getUrl(),
                getHttpMethod(),
                new HttpEntity<>(getHttpHeaders(token)),
                getResponseType());

        validateResponse(response);

        log.info("Get locations. Response={}", response.getBody());

        return response.getBody()
                .stream()
                .sorted(getTrendLocationComparator())
                .collect(Collectors.toList());
    }

    private HttpHeaders getHttpHeaders(Token token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.set("Authorization", token.getTokenType() + " " + token.getAccessToken());
        return headers;
    }

    void validateResponse(ResponseEntity<List<TrendLocation>> response) {
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new IllegalStateException("Http status = " + response.getStatusCode());
        }

        if (response.getBody() == null) {
            throw new IllegalStateException("Response body is null");
        }
    }

    private Comparator<TrendLocation> getTrendLocationComparator() {
        return Comparator.comparing(TrendLocation::getCountry);
    }

    private HttpMethod getHttpMethod() {
        return HttpMethod.GET;
    }

    private ParameterizedTypeReference<List<TrendLocation>> getResponseType() {
        return new ParameterizedTypeReference<List<TrendLocation>>() {
        };
    }

    private String getUrl() {
        return twitterUrl + endpoint;
    }
}
