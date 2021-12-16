package com.epam.brest.dao.dto;

import com.epam.brest.dao_api.dto.DriverDtoDao;
import com.epam.brest.model.dto.DriverDto;
import com.epam.brest.test_db.config.SpringJdbcConfig;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
@Import({DriverDtoDaoJdbcImpl.class})
@ContextConfiguration(classes = SpringJdbcConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback
class DriverDtoDaoJdbcImplTestIT {

    public static final Logger LOG = LoggerFactory.getLogger(DriverDtoDaoJdbcImplTestIT.class);

    private DriverDtoDaoJdbcImpl driverDtoDaoJdbc;

    public DriverDtoDaoJdbcImplTestIT(@Autowired DriverDtoDao driverDtoDaoJdbc) {
        this.driverDtoDaoJdbc = (DriverDtoDaoJdbcImpl) driverDtoDaoJdbc;
    }

    @Test
    void findWithCountCars() {
        LOG.info("Method started: findWithCountCars() of {}", getClass().getName());
        List<DriverDto> drivers = driverDtoDaoJdbc.findAllDriversWithCountCars();
        assertNotNull(drivers);
        assertTrue(drivers.size() > 0);
        LOG.info("List of driver Dto was created {}", drivers);
        assertTrue(drivers.get(0).getCountOfCarsAssignedToDriver() > 0);
    }

    @Test
    void chooseDriverOnDateRange() {
        LOG.info("Method started: chooseDriverOnDateRange() of {}", getClass().getName());
        String fromDate = "1990-01-02T10:10:10.002Z";
        String toDate = "2021-01-02T10:10:10.002Z";
        List<DriverDto> drivers = driverDtoDaoJdbc.chooseDriverOnDateRange(fromDate, toDate);
        assertNotNull(drivers);
        LOG.info("List of driver Dto was created {}", drivers);
    }
}