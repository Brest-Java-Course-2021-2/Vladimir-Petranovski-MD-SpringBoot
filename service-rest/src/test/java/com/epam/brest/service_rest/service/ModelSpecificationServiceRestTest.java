package com.epam.brest.service_rest.service;

import com.epam.brest.model.ModelSpecification;
import com.epam.brest.service_rest.service.config.ServiceRestTestConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@ExtendWith(SpringExtension.class)
@Import({ServiceRestTestConfig.class})
class ModelSpecificationServiceRestTest {

    public static final Logger LOG = LogManager.getLogger(
            ModelSpecificationServiceRestTest.class);

    public static final String MODEL_INFO_URL = "http://localhost:8088/model_info";

    @Autowired
    private RestTemplate restTemplate;

    private ModelSpecificationServiceRest modelSpecificationServiceRest;

    private MockRestServiceServer mockRestServiceServer;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        modelSpecificationServiceRest = new ModelSpecificationServiceRest(MODEL_INFO_URL, restTemplate);
        mockRestServiceServer = MockRestServiceServer.createServer(restTemplate);
        objectMapper = new ObjectMapper().findAndRegisterModules();
    }

    @Test
    void getModelSpecificationByCarModel() throws URISyntaxException, JsonProcessingException {
        LOG.info("Method getModelSpecificationByCarModel() started {}",
                getClass().getName());
        // given
        String carModel = "NISSAN";
        ModelSpecification modelSpecification = new ModelSpecification(
                1, carModel, "Passenger car: made in Japan",
                210, 1740);

        mockRestServiceServer.expect(ExpectedCount.once(), requestTo(new URI(MODEL_INFO_URL + "/" + modelSpecification.getModelName())))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(objectMapper.writeValueAsString(modelSpecification))
                );
        // when
        ModelSpecification modelSpecificationDst = modelSpecificationServiceRest.getModelSpecificationByCarModel(carModel);
        // then
        mockRestServiceServer.verify();
        assertNotNull(modelSpecificationDst);
        assertEquals(modelSpecificationDst.getModelName(), carModel);
        assertEquals(modelSpecificationDst.getModelId(), modelSpecification.getModelId());
    }
}