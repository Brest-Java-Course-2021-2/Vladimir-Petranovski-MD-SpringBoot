package com.epam.brest.mongodb_postgresql.mapper;

import com.epam.brest.dao_api.CarDao;
import com.epam.brest.model.Car;
import com.epam.brest.model.Driver;
import com.epam.brest.mongodb_postgresql.model.DriverDtoMongodb;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DriverMongodbFromPostgresqlMapper {

    /**
     * @serialField carDaoJdbc CarDaoJdbcImpl.
     */

    private final CarDao carDao;

    /**
     * @Constructor
     *
     * @param carDao CarDaoJdbcImpl.
     */

    public DriverMongodbFromPostgresqlMapper(final CarDao carDao) {
        this.carDao = carDao;
    }

    /**
     * Mapper for driver from PostgreSQL into MongoDB.
     *
     * @return driverDtoMongodb DriverDtoMongodb.
     */

    public DriverDtoMongodb getDriverMongodbFromPostgresql(final Driver driver) {

        List<Car> assignCars = carDao.getCarsAssignToDriverById(driver.getDriverId());

        DriverDtoMongodb driverDtoMongodb = new DriverDtoMongodb();

        driverDtoMongodb.setDriverId(driver.getDriverId());
        driverDtoMongodb.setDriverName(driver.getDriverName());
        driverDtoMongodb.setDriverSalary(driver.getDriverSalary());
        driverDtoMongodb.setDriverDateStartWork(driver.getDriverDateStartWork());
        driverDtoMongodb.setAssignCars(assignCars.toArray(new Car[0]));
        return driverDtoMongodb;
    }
}
