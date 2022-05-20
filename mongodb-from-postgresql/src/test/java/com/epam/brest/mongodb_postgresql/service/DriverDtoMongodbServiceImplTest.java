package com.epam.brest.mongodb_postgresql.service;

import com.epam.brest.dao_api.DriverDao;
import com.epam.brest.model.Car;
import com.epam.brest.model.Driver;
import com.epam.brest.mongodb_postgresql.model.DriverDtoMongodb;
import com.epam.brest.mongodb_postgresql.repository.DriverDtoMongodbRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DriverDtoMongodbServiceImplTest {

    public static final Logger LOG = LogManager.getLogger(DriverDtoMongodbServiceImplTest.class);

    @InjectMocks
    private DriverDtoMongodbServiceImpl driverDtoMongodbService;

    @Spy
    private DriverDtoMongodbServiceImpl driverDtoMongodbServiceTest;

    @Mock
    private DriverDtoMongodbRepository driverDtoMongodbRepository;

    @Mock
    private DriverDao driverDao;

    private DriverDtoMongodb driverDtoMongodbOne;

    private List<DriverDtoMongodb> drivers;

    private List<Driver> driverList;

    @BeforeEach
    void setUp() {
        driverDtoMongodbOne = new DriverDtoMongodb(1, "VASIA",
                Instant.parse("1998-10-01T12:02:01.8472Z"), BigDecimal.valueOf(500),
                new Car[]{new Car("SCKODA",3), new Car("JILY", 2)});
        DriverDtoMongodb driverDtoMongodbTwo = new DriverDtoMongodb(2, "VOVA",
                Instant.parse("2010-10-11T08:30:30.1234Z"), BigDecimal.valueOf(850),
                new Car[]{new Car("AUDI", 2), new Car("NISSAN", 1)});
        drivers = new ArrayList<>();
        drivers.add(driverDtoMongodbOne);
        drivers.add(driverDtoMongodbTwo);

        Driver driverOne = new Driver(1, "VASIA",
                Instant.parse("1998-10-01T12:02:01.8472Z"), BigDecimal.valueOf(500));
        Driver driverTwo = new Driver(2, "VOVA",
                Instant.parse("2010-10-11T08:30:30.1234Z"), BigDecimal.valueOf(850));
        driverList = new ArrayList<>();
        driverList.add(driverOne);
        driverList.add(driverTwo);
    }

    @Test
    void findAllDriversMongodbTest() {

        LOG.info("Method findAllDriversMongodbTest() of class {} started", getClass().getName());

        when(driverDtoMongodbRepository.findAll()).thenReturn(drivers);

        doNothing().when(driverDtoMongodbRepository).deleteAll();

        lenient().doNothing().when(driverDtoMongodbServiceTest).createDriversCollection();

        List<DriverDtoMongodb> driversDst = driverDtoMongodbService.findAllDriversMongodb();

        verify(driverDtoMongodbRepository, times(1)).deleteAll();
        verify(driverDtoMongodbRepository, times(1)).findAll();

        assertNotNull(driverDtoMongodbService);
        assertNotNull(drivers);
        assertNotNull(driversDst);
        assertFalse(driversDst.isEmpty());
        assertSame(driverDtoMongodbOne, driversDst.get(0));
        assertSame(driversDst.size(), drivers.size());
        LOG.info("Size driver's list after findAllDrivers() {} the same before {}", driversDst.size(), drivers.size());
    }

    @Test
    void createDriversCollectionTest() {
        LOG.info("Method createDriversCollectionTest() of class {} started", getClass().getName());

        lenient().when(driverDao.findAllDrivers()).thenReturn(driverList);
        lenient().when(driverDtoMongodbRepository.save(any(DriverDtoMongodb.class))).thenReturn(driverDtoMongodbOne);

        List<Driver> driversListTest = driverDao.findAllDrivers();
        DriverDtoMongodb driverDtoMongodb = driverDtoMongodbRepository.save(driverDtoMongodbOne);

        verify(driverDao, times(1)).findAllDrivers();
        verify(driverDtoMongodbRepository, times(1)).save(eq(driverDtoMongodbOne));

        assertNotNull(driversListTest);
        assertNotNull(driverDtoMongodb);
        assertFalse(driversListTest.isEmpty());
        assertSame(driverDtoMongodb, driverDtoMongodbOne);
        LOG.info("Mongodb's driver after save() {} the same before {}", driverDtoMongodb, driverDtoMongodbOne);
    }
}