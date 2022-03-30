package com.epam.brest.controller;

import com.gargoylesoftware.htmlunit.WebClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * By default, @SpringBootTest will not start a server.
 * Read please https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.testing.spring-boot-applications
 */
@SpringBootTest
@AutoConfigureMockMvc
public class SmokeIT {

    public static final Logger LOG = LogManager.getLogger(SmokeIT.class);

    /**
     * cause rest-server is not up and running
     * rest-client should be tested via MockRestServer
     */

    @Autowired
    private ApplicationContext context;

    private WebClient webClient;

    @BeforeEach
    void setup(final WebApplicationContext webContext) {
        webClient = MockMvcWebClientBuilder
                .webAppContextSetup(webContext)
                .build();
        webClient.getOptions().setJavaScriptEnabled(false);
    }

    @Test
    void contextLoads() {
        assertNotNull(context);
        LOG.info("The Spring context has been loaded successfully");
    }
}
