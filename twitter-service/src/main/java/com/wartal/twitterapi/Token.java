package com.wartal.twitterapi;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by lwartalski on 10/10/16.
 */

public class Token extends RestResponse {

    @JsonProperty(value = "token_type")
    private String tokenType;
    @JsonProperty(value = "access_token")
    private String accessToken;

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
