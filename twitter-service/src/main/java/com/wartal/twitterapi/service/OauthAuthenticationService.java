package com.wartal.twitterapi.service;

/**
 * Created by lwartalski on 11/10/16.
 */
public interface OauthAuthenticationService<T> {
    /**
     * Get token for oauth requests
     *
     * @return
     */
    T getToken();
}
