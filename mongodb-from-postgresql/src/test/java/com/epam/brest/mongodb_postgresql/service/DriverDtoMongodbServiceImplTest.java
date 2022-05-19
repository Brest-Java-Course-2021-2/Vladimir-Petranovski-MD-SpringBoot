package com.epam.brest.mongodb_postgresql.service;

import com.epam.brest.dao_api.DriverDao;
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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.jdbc.support.KeyHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DriverDtoMongodbServiceImplTest {

    public static final Logger LOG = LogManager.getLogger(DriverDtoMongodbServiceImplTest.class);

    @InjectMocks
    private DriverDtoMongodbServiceImpl driverDtoMongodbService;

    @Mock
    private DriverDtoMongodbRepository driverDtoMongodbRepository;

    @Mock
    private DriverDao driverDao;

    private DriverDtoMongodb driverDtoMongodbOne;

    private DriverDtoMongodb driverDtoMongodbTwo;

    private List<DriverDtoMongodb> drivers;

    @BeforeEach
    void setUp() {
        driverDtoMongodbOne = new DriverDtoMongodb();
        driverDtoMongodbTwo = new DriverDtoMongodb();
        drivers = new ArrayList<>();
        drivers.add(driverDtoMongodbOne);
        drivers.add(driverDtoMongodbTwo);
    }

    @Test
    void findAllDriversMongodbTest() {

        LOG.info("Method findAllDriversMongodbTest() of class {} started", getClass().getName());

//        when(driverDtoMongodbRepository.findAll()).thenReturn(drivers);
//
//        Mockito.doAnswer(a -> {
//            driverDtoMongodbRepository.deleteAll();
//            return null;
//        }).when(driverDtoMongodbRepository).findAll();
////        Mockito.doAnswer(a -> {
////            driverDtoMongodbService.createDriversCollection();
////            return null;
////        }).when(driverDtoMongodbRepository).findAll();
////        Mockito.doAnswer(a -> {
////            driverDtoMongodbService.createDriversCollection();
////            return null;
////        }).when(driverDtoMongodbRepository).findAll();
//
//        List<DriverDtoMongodb> driversDst = driverDtoMongodbService.findAllDriversMongodb();
//
////        verify(driverDtoMongodbRepository, times(1)).deleteAll();
//        verify(driverDtoMongodbRepository, times(1)).findAll();
//
//        assertNotNull(drivers);
//        assertNotNull(driversDst);
//        assertFalse(driversDst.isEmpty());
//        assertSame(driverDtoMongodbOne, driversDst.get(1));
//        assertSame(driversDst.size(), drivers.size());
//        LOG.info("Size driver's list after findAllDrivers() {} the same before {}", driversDst.size(), drivers.size());
    }

    @Test
    void createDriversCollection() {
    }
}