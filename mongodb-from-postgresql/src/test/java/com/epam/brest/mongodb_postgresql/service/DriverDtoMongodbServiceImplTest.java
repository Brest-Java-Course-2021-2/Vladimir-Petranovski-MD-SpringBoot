package com.epam.brest.mongodb_postgresql.service;

import com.epam.brest.model.Driver;
import com.epam.brest.mongodb_postgresql.model.DriverDtoMongodb;
import com.epam.brest.mongodb_postgresql.repository.DriverDtoMongodbRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DriverDtoMongodbServiceImplTest {

    public static final Logger LOG = LogManager.getLogger(DriverDtoMongodbServiceImplTest.class);

    @InjectMocks
    private DriverDtoMongodbServiceImpl driverDtoMongodbService;

    @Mock
    private DriverDtoMongodbRepository driverDtoMongodbRepository;

    private DriverDtoMongodb driverDtoMongodbOne;

    private DriverDtoMongodb driverDtoMongodbTwo;

    private List<DriverDtoMongodb> drivers;

    @BeforeEach
    void setUp() {
        drivers = new ArrayList<>();
        drivers.add(driverDtoMongodbOne);
        drivers.add(driverDtoMongodbTwo);
    }

    @Test
    void findAllDriversMongodbTest() {

        LOG.info("Method findAllDriversMongodbTest() of class {} started", getClass().getName());

        when(driverDtoMongodbRepository.findAll()).thenReturn(drivers);

        List<DriverDtoMongodb> driversDst = driverDtoMongodbService.findAllDriversMongodb();

        verify(driverDtoMongodbRepository, times(1)).findAll();

        assertNotNull(drivers);
        assertFalse(drivers.isEmpty());
        assertSame(driverDtoMongodbOne, driversDst.get(1));
        assertSame(driversDst.size(), drivers.size());
        LOG.info("Size driver's list after findAllDrivers() {} the same before {}", driversDst.size(), drivers.size());
    }
}