package com.epam.brest.service_rest.service.dto;

import com.epam.brest.mongodb_postgresql.model.DriverDtoMongodb;
import com.epam.brest.service_rest.service.config.ServiceRestTestConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@ExtendWith(SpringExtension.class)
@Import({ServiceRestTestConfig.class})
class DriverDtoMongodbServiceRestTest {

    public static final Logger LOG = LogManager.getLogger(
            DriverDtoMongodbServiceRestTest.class);

    public static final String DRIVERS_DTO_MONGODB_URL = "http://localhost:8088/mongo";

    @Autowired
    private RestTemplate restTemplate;

    private DriverDtoMongodbServiceRest driverDtoServiceRest;

    private MockRestServiceServer mockRestServiceServer;

    private ObjectMapper objectMapper;


    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        driverDtoServiceRest = new DriverDtoMongodbServiceRest(DRIVERS_DTO_MONGODB_URL, restTemplate);
        mockRestServiceServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    void findAllDriversMongodbTest() throws JsonProcessingException, URISyntaxException {

        LOG.info("Method findAllDriversMongodbTest() started {}",
                getClass().getName());

        // given
        mockRestServiceServer.expect(ExpectedCount.once(), requestTo(new URI(DRIVERS_DTO_MONGODB_URL)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(objectMapper.writeValueAsString(Arrays.asList(create(0), create(1))))
                );
        // when
        List<DriverDtoMongodb> drivers = driverDtoServiceRest.findAllDriversMongodb();
        // then
        mockRestServiceServer.verify();
        assertNotNull(drivers);
        assertTrue(drivers.size() > 0);
    }

    private DriverDtoMongodb create(Integer index) {
        DriverDtoMongodb driverDto = new DriverDtoMongodb();
        driverDto.setDriverId(index);
        driverDto.setDriverName("d" + index);
        driverDto.setDriverDateStartWork(new Date(index).toInstant());
        driverDto.setDriverSalary(BigDecimal.valueOf(100 + index));
        return driverDto;
    }
}