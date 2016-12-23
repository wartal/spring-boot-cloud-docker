package com.wartal.twitterapi.service;

import com.wartal.twitterapi.Token;
import com.wartal.twitterapi.Trend;
import com.wartal.twitterapi.TrendWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * GET trends/place
 * <p>
 * Created by lwartalski on 10/10/16.
 */
@Service
public class TrendRestService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Value("${twitter.endpoint.trend.place}")
    private String endpoint;

    @Value("${twitter.api.url}")
    private String twitterUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AuthenticationService authenticationService;

    /**
     * Get list of trends for given location
     *
     * @param woeid location id
     * @return
     */
    public List<Trend> invoke(Integer woeid) {
        validateInputParam(woeid);
        final Token token = authenticationService.getToken();
        log.info("Get trends. Woeid={} Token={}", woeid, token);

        final URI uri = UriComponentsBuilder.fromHttpUrl(getUrl())
                .queryParam("id", woeid)
                .build()
                .encode()
                .toUri();

        final ResponseEntity<List<TrendWrapper>> response = restTemplate.exchange(
                uri,
                getHttpMethod(),
                new HttpEntity<>(getHttpHeaders(token)),
                getResponseType());

        validateResponse(response);

        log.info("Get trends. Response={}", response.getBody());

        return response.getBody().get(0).getTrends();
    }

    private HttpHeaders getHttpHeaders(Token token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.set("Authorization", token.getTokenType() + " " + token.getAccessToken());
        return headers;
    }

    void validateInputParam(Integer woeid) {
        if (woeid == null) {
            throw new RuntimeException("Invalid input parameter=" + woeid);
        }
    }

    void validateResponse(ResponseEntity<List<TrendWrapper>> response) {
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new IllegalStateException("Http status = " + response.getStatusCode());
        }

        if (response.getBody() == null) {
            throw new IllegalStateException("Response body is null");
        }

        if (response.getBody().get(0) == null) {
            throw new IllegalStateException("Response body is empty");
        }
    }

    private HttpMethod getHttpMethod() {
        return HttpMethod.GET;
    }

    private ParameterizedTypeReference<List<TrendWrapper>> getResponseType() {
        return new ParameterizedTypeReference<List<TrendWrapper>>() {
        };
    }

    private String getUrl() {
        return twitterUrl + endpoint;
    }
}
