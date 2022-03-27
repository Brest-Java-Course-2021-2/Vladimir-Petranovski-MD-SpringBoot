package com.epam.brest.controller;

import com.epam.brest.model.Driver;
import com.epam.brest.model.dto.DriverDto;
import com.epam.brest.service_api.DriverService;
import com.epam.brest.service_api.dto.DriverDtoService;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * By default, @SpringBootTest will not start a server.
 * Read please https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.testing.spring-boot-applications
 */
@SpringBootTest
@AutoConfigureMockMvc
public class SmokeIT {

    public static final Logger LOG = LogManager.getLogger(SmokeIT.class);

    @Autowired
    private MockMvc mockMvc;

    /**
     * cause rest-server is not up and running
     * rest-client should be tested via MockRestServer
     */
    @MockBean
    private DriverDtoService restDriverService;

    // TODO: DepartmentDtoService vs DepartmentService
    @MockBean
    private DriverService driverService;

    @Autowired
    private ApplicationContext context;

    private WebClient webClient;

    @BeforeEach
    void setup(final WebApplicationContext webContext) {
        webClient = MockMvcWebClientBuilder
                .webAppContextSetup(webContext)
                .build();
        // TODO: why do we need to set false
        webClient.getOptions().setJavaScriptEnabled(false);
    }

    @Test
    void contextLoads() {
        // just to show how you can look at the current spring context
        assertNotNull(context);
        LOG.info("The Spring context has been loaded successfully");
    }

//    /**
//     * https://docs.spring.io/spring-framework/docs/current/reference/html/testing.html#spring-mvc-test-server-htmlunit-mah
//     *
//     * @throws Exception an error
//     */
//    @Test
//    void openHomeAndClickDriver() throws Exception {
//        emulateRestServer();
//
//        LOG.info("1. Home page");
//        final HtmlPage page = webClient.getPage("http://localhost/");
//        final HtmlAnchor link = (HtmlAnchor) page.getByXPath("//a[@href='/drivers/123']").get(0);
//
//        LOG.info("2. Click on the driver");
//        final HtmlPage detailsPage =  link.click();
//        final HtmlTextInput driverName = (HtmlTextInput) detailsPage.getElementById("field_driverName");
//
//        assertEquals("test-driver", driverName.getText());
//    }
//
//    /**
//     * Note that we're not gonna up web-app server cause we have to run rest-server too (that's not trivial with maven).
//     */
//    private void emulateRestServer() {
//        // to emulate rest-server
//        final DriverDto testDriver = new DriverDto();
//        testDriver.setDriverId(123);
//
//        final Driver driver = new Driver();
//        driver.setDriverName("test-driver");
//
//        when(restDriverService.findAllDriverWithCountCars()).thenReturn(Collections.singletonList(testDriver));
//        when(driverService.findDriverById(123)).thenReturn(driver);
//    }
}
