package com.wartal.twitterapi.service;

import com.wartal.twitterapi.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;

/**
 * Oauth authentication service
 * Created by lwartalski on 10/10/16.
 */
@Service
public class AuthenticationService implements OauthAuthenticationService<Token> {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Value("${twitter.api.key}")
    private String twitterApiKey;

    @Value("${twitter.api.secret}")
    private String twitterApiSecret;

    @Value("${twitter.api.url}")
    private String twitterUrl;

    @Value("${twitter.endpoint.oauth}")
    private String endpoint;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Token getToken() {
        log.info("get token");
        ResponseEntity<Token> response = restTemplate.postForEntity(getUrl(),
                new HttpEntity<>(getParameters(), getHttpHeaders()), Token.class);

        validateResponse(response);
        return response.getBody();
    }

    private String getUrl() {
        return twitterUrl + endpoint;
    }

    private MultiValueMap<String, String> getParameters() {
        final MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("grant_type", "client_credentials");
        return parameters;
    }

    private void validateResponse(ResponseEntity<Token> response) {
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new IllegalStateException("Http status = " + response.getStatusCode());
        }
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + getEncodedCredentials());
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return headers;
    }

    private String getEncodedCredentials() {
        return Base64Utils.encodeToString(stringToBytes(twitterApiKey, ":", twitterApiSecret));
    }

    private byte[] stringToBytes(String... str) {
        return stream(str).collect(joining()).getBytes();
    }
}
