package com.wartal;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
 * Created by lwartalski on 13/10/16.
 */
@org.springframework.boot.test.context.TestConfiguration
@ComponentScan("com.wartal")
public class TestConfiguration {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().build();
    }
}
