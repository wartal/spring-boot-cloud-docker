package com.wartal.twitterapi.service;

import com.wartal.TestConfiguration;
import com.wartal.twitterapi.Token;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by lwartalski on 10/10/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AuthenticationService.class, TestConfiguration.class})
public class AuthenticationServiceIntegrationTest {

    @Autowired
    private AuthenticationService authenticationService;

    @Test
    public void shouldGetTokenTest() {
        final Token token = authenticationService.getToken();
        assertNotNull(token);
        assertEquals("bearer", token.getTokenType());
        assertNotNull(token.getAccessToken());
    }
}