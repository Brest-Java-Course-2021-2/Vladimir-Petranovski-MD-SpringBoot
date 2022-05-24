package com.epam.brest.dao;

import com.epam.brest.model.Car;
import com.epam.brest.test_db.config.SpringJdbcConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
@Import({CarDaoJdbcImpl.class})
@ContextConfiguration(classes = SpringJdbcConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback
@ActiveProfiles({"dev", "test"})
class CarDaoJdbcImplTestIT {

    public static final Logger LOG = LogManager.getLogger(CarDaoJdbcImplTestIT.class);

    @Autowired
    private CarDaoJdbcImpl carDaoJdbc;

    @Test
    void findAllCars() {
        LOG.info("Method started: findAllCars() of {}", getClass().getName());
        assertNotNull(carDaoJdbc);
        assertNotNull(carDaoJdbc.findAllCars());
        LOG.info("{}", carDaoJdbc.findAllCars());
    }

    @Test
    void findCarById() {
        LOG.info("Method started: findCarById() of {}", getClass().getName());
        assertNotNull(carDaoJdbc);
        List<Car> cars = carDaoJdbc.findAllCars();
        if(cars.size() == 0) {
            carDaoJdbc.saveCar(new Car("LUAZ", 3));
            cars = carDaoJdbc.findAllCars();
        }
        Car carSrc = cars.get(0);
        Car carDst = carDaoJdbc.findCarById(carSrc.getCarId());
        assertEquals(carSrc.getCarModel(), carDst.getCarModel());
        LOG.info("Car's model first from list: {} equals car's model after finding: {}", carSrc.getCarModel(), carDst.getCarModel());
    }

    @Test
    void saveCar() {
        LOG.info("Method started: saveCar() of {}", getClass().getName());
        assertNotNull(carDaoJdbc);
        int countCarBeforeSave = carDaoJdbc.count();
        Car car = new Car("GAZON", 3);
        carDaoJdbc.saveCar(car);
        int countCarAfterSave = carDaoJdbc.count();
        assertEquals(countCarBeforeSave, countCarAfterSave - 1);
        LOG.info("Record's count before save():{} equals record's count after save minus one: {}", countCarBeforeSave, countCarAfterSave - 1);

    }

    @Test
    void updateCarById() {
        LOG.info("Method started: updateCarById() of {}", getClass().getName());
        assertNotNull(carDaoJdbc);
        List<Car> cars = carDaoJdbc.findAllCars();
        if(cars.size() == 0) {
            carDaoJdbc.saveCar(new Car("LUAZ", 3));
            cars = carDaoJdbc.findAllCars();
        }
        Car carSrc = cars.get(0);
        carSrc.setCarModel(carSrc.getCarModel() + "_TEST");
        carDaoJdbc.updateCarById(carSrc.getCarId(), carSrc);

        Car carDst = carDaoJdbc.findCarById(carSrc.getCarId());
        assertEquals(carSrc.getCarModel(), carDst.getCarModel());
        LOG.info("Driver's name first from list: {} equals driver's name after updating: {}", carSrc.getCarModel(), carDst.getCarModel());
    }

    @Test
    void deleteCarById() {
        LOG.info("Method started: deleteCarById() of {}", getClass().getName());
        assertNotNull(carDaoJdbc);
        carDaoJdbc.saveCar(new Car("LUAZ", 3));
        List<Car> cars = carDaoJdbc.findAllCars();

        Car carSrc = carDaoJdbc.findCarById(cars.size() - 1);
        carDaoJdbc.deleteCarById(carSrc.getCarId());
        assertEquals(cars.size() -1, carDaoJdbc.findAllCars().size());
        LOG.info("First car's size list minus one: {} equals car's size list after deleting {}", cars.size() -1, carDaoJdbc.findAllCars().size());
    }

    @Test
    void checkCount() {
        LOG.info("Method started: checkCount() of {}", getClass().getName());
        assertNotNull(carDaoJdbc);
        Integer quantity = carDaoJdbc.count();
        assertNotNull(quantity);
        assertTrue(quantity > 0);
        LOG.info("Quantity equals {}", quantity);
        assertEquals(carDaoJdbc.findAllCars().size(), quantity);
        LOG.info("Size of collection {} equals record's quantity {}", carDaoJdbc.findAllCars().size(), quantity);
    }

    @Test
    void getCarsAssignToDriverTest() {
        LOG.info("Method started: getCarsAssignToDriverTest() of {}", getClass().getName());
        assertNotNull(carDaoJdbc);
        Integer countCarInTheBeginning = carDaoJdbc.getCarsAssignToDriverById(2).size();
        carDaoJdbc.saveCar(new Car("LUAZ", 2));
        carDaoJdbc.saveCar(new Car("URAL", 2));
        carDaoJdbc.saveCar(new Car("ZIL", 2));

        int countCarsAfterSave = carDaoJdbc.getCarsAssignToDriverById(2).size();
        assertEquals(countCarInTheBeginning, countCarsAfterSave - 3);
    }
}