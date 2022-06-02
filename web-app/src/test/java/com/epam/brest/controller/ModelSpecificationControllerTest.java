package com.epam.brest.controller;

import com.epam.brest.model.ModelSpecification;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.net.URI;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class ModelSpecificationControllerTest {

    public static final Logger LOG = LogManager.getLogger(ModelSpecificationControllerTest.class);

    public static final String MODEL_INFO_URL = "http://localhost:8088/model_info";

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockRestServiceServer;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        mockRestServiceServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    void shouldGetModelSpecificationByCarName() throws Exception {
        LOG.info("Method shouldGetModelSpecificationByCarName() started {}",
                getClass().getName());

        ModelSpecification modelSpecificationSrs = new ModelSpecification(1, "VOLVO",
                "Truck: made in China", 150, 25000);

        mockRestServiceServer.expect(ExpectedCount.once(), requestTo(new URI(MODEL_INFO_URL + "/" + modelSpecificationSrs.getModelName())))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(objectMapper.writeValueAsString(modelSpecificationSrs))
                );

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/model_info/" + modelSpecificationSrs.getModelName())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("model", modelSpecificationSrs.getModelName())
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("specification", hasProperty("modelId", is(modelSpecificationSrs.getModelId()))))
                .andExpect(model().attribute("specification", hasProperty("modelName", is(modelSpecificationSrs.getModelName()))))
                .andExpect(model().attribute("specification", hasProperty("description", is(modelSpecificationSrs.getDescription()))))
                .andExpect(model().attribute("specification", hasProperty("maxSpeed", is(modelSpecificationSrs.getMaxSpeed()))))
                .andExpect(model().attribute("specification", hasProperty("carryingCapacity", is(modelSpecificationSrs.getCarryingCapacity()))))
                .andExpect(view().name("cars/cars"));
        mockRestServiceServer.verify();
    }
}