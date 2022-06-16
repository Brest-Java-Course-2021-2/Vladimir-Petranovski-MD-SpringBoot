package com.epam.brest.rest.controller;

import com.epam.brest.model.ModelSpecification;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@Transactional
@Profile("dev, test")
class ModelSpecificationControllerIT {

    public static final Logger LOG = LogManager.getLogger(ModelSpecificationControllerIT.class);

    public static final String MODEL_ENDPOINT = "/model_info";

    @Autowired
    private ModelSpecificationController modelSpecificationController;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    MockMvcModelSpecificationService modelSpecificationService = new MockMvcModelSpecificationService();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(modelSpecificationController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
//                .setControllerAdvice(customExceptionHandler)
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }

    @Test
    void getModelSpecificationByCarModelTest() throws Exception {
        LOG.info("Method getModelSpecificationByCarModelTest() started of class {}", getClass().getName());

        // I should make CRUD operations for this test!!!!!!!!
//        //given
//        ModelSpecification specificationSrc = new ModelSpecification(
//                "URAL", "Passenger car: made in Japan", 200, 1870);
//        assertNotNull(specificationSrc);
////        Integer id = driverService.saveDriver(driver);
//        String carModel = specificationSrc.getModelName();
//        assertNotNull(carModel);
//        // when
//        ModelSpecification specificationDst = modelSpecificationService.getSpecificationByModelName(carModel);
//        // then
//        assertNotNull(specificationDst);
//        assertEquals(specificationDst.getModelName(), carModel);
//        assertEquals(specificationSrc.getModelName(), specificationDst.getModelName());
    }

    class MockMvcModelSpecificationService {

        public ModelSpecification getSpecificationByModelName(final String carModel) throws Exception {
            LOG.info("Method getSpecificationByModelName() with car's model: {} started of class {}", carModel, getClass().getName());

            MockHttpServletResponse response = mockMvc.perform(
                            MockMvcRequestBuilders.get(MODEL_ENDPOINT + "/" + carModel)
                                    .accept(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isOk())
                    .andReturn().getResponse();
            assertNotNull(response);
            return objectMapper.readValue(response.getContentAsString(), ModelSpecification.class);
        }
    }
}