package com.epam.brest.service.impl;

import com.epam.brest.dao_api.ModelSpecificationDao;
import com.epam.brest.model.ModelSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ModelSpecificationServiceImplTest {

    private static final Logger LOG = LogManager.getLogger(ModelSpecificationServiceImplTest.class);

    @InjectMocks
    private ModelSpecificationServiceImpl modelSpecificationService;

    @Mock
    private ModelSpecificationDao modelSpecificationDao;

    private ModelSpecification modelSpecification;

    @BeforeEach
    void setUp() {
        modelSpecification = new ModelSpecification(
                "NISSAN", "Passenger car: made in Japan", 200, 1870);
    }

    @Test
    void getModelSpecificationByCarModel() {
        LOG.info("Method getModelSpecificationByCarModel() of class {} started", getClass().getName());

        lenient().when(modelSpecificationDao.getModelSpecificationByCarModel(anyString()))
                .thenReturn(modelSpecification);

        ModelSpecification modelSpecificationDst = modelSpecificationService
                .getModelSpecificationByCarModel(modelSpecification.getModelName());

        verify(modelSpecificationDao, times(1))
                .getModelSpecificationByCarModel(eq(modelSpecification.getModelName()));

        assertNotNull(modelSpecificationDst);
        assertEquals(modelSpecification, modelSpecificationDst);
        LOG.info("ModelSpecification was received after getModelSpecificationByCarModel() {} equals modelSpecification till it {}",
                modelSpecificationDst, modelSpecification);
    }
}