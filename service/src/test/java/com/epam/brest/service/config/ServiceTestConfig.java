package com.epam.brest.service.config;

import com.epam.brest.dao.CarDaoJdbcImpl;
import com.epam.brest.dao.DriverDaoJdbcImpl;
import com.epam.brest.dao.dto.DriverDtoDaoJdbcImpl;
import com.epam.brest.dao_api.CarDao;
import com.epam.brest.dao_api.DriverDao;
import com.epam.brest.dao_api.dto.DriverDtoDao;
import com.epam.brest.service.impl.CarServiceImpl;
import com.epam.brest.service.impl.DriverServiceImpl;
import com.epam.brest.service.impl.dto.DriverDtoServiceImpl;
import com.epam.brest.service_api.CarService;
import com.epam.brest.service_api.DriverService;
import com.epam.brest.service_api.dto.DriverDtoService;
import com.epam.brest.test_db.config.SpringJdbcConfig;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ServiceTestConfig extends SpringJdbcConfig {

    @Bean
    DriverDtoDao driverDtoDao() {
        return new DriverDtoDaoJdbcImpl(namedParameterJdbcTemplate());
    }

    @Bean
    DriverDtoService driverDtoService() {
        return new DriverDtoServiceImpl(driverDtoDao());
    }

    @Bean
    DriverDao driverDao() {
        return new DriverDaoJdbcImpl(namedParameterJdbcTemplate());
    }

    @Bean
    DriverService driverService() {
        return new DriverServiceImpl(driverDao());
    }

    @Bean
    CarDao carDao() {
        return new CarDaoJdbcImpl(namedParameterJdbcTemplate());
    }

    @Bean
    CarService carService() {
        return new CarServiceImpl(carDao());
    }
}
