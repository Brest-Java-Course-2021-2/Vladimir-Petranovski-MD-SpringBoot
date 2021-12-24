package com.epam.brest.service_rest.service.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@TestConfiguration
public class ServiceRestTestConfig {

    /**
     * Bean restTemplate RestTemplate.
     *
     * @return restTemplate.
     */

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
