package com.epam.brest.rest.controller.dto;

import com.epam.brest.mongodb_postgresql.model.DriverDtoMongodb;
import com.epam.brest.mongodb_postgresql.service.DriverDtoMongodbService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class DriverDtoMongodbControllerTest {

    public static final Logger LOG = LogManager.getLogger(DriverDtoMongodbControllerTest.class);

    @InjectMocks
    private DriverDtoMongodbController driverDtoMongodbController;

    @Mock
    private DriverDtoMongodbService driverDtoService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(driverDtoMongodbController)
                .build();
    }

    @AfterEach
    public void end() {
        verifyNoMoreInteractions(driverDtoService);
    }

    @Test
    void findAllDriversFromMongoTest() throws Exception {
        LOG.info("Method findAllDriversFromMongoTest() started of class {}", getClass().getName());

        when(driverDtoService.findAllDriversMongodb()).thenReturn(Arrays.asList(create(0), create(1)));

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/mongo")
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].driverId", Matchers.is(0)))
                .andExpect(jsonPath("$[0].driverName", Matchers.is("d0")))
                .andExpect(jsonPath("$[0].driverDateStartWork", Matchers.is(0.0)))
                .andExpect(jsonPath("$[0].driverSalary", Matchers.is(100)))
                .andExpect(jsonPath("$[1].driverId", Matchers.is(1)))
                .andExpect(jsonPath("$[1].driverName", Matchers.is("d1")))
                .andExpect(jsonPath("$[1].driverDateStartWork", Matchers.is(0.001)))
                .andExpect(jsonPath("$[1].driverSalary", Matchers.is(101)));

        verify(driverDtoService, times(1)).findAllDriversMongodb();
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