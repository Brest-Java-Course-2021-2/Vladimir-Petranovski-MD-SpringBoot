package com.epam.brest.dao_api;

import com.epam.brest.model.Car;

import java.util.List;

public interface CarDao {

    /**
     * Find all cars.
     *
     * @return list of cars.
     */

    List<Car> findAllCars();

    /**
     * Find car by Id.
     *
     * @param id car Id.
     * @return car.
     */

    Car findCarById(final Integer id);

    /**
     * Persist new car.
     *
     * @param car car.
     * @return persisted car id.
     */

    Integer saveCar(final Car car);

    /**
     * Update car.
     *
     * @param id car id.
     * @param car car.
     * @return number of updated records in the database.
     */

    Integer updateCarById(final Integer id, final Car car);

    /**
     * Delete car.
     *
     * @param id car id.
     * @return number of updated records in the database.
     */

    Integer deleteCarById(final Integer id);

    /**
     * Count cars.
     *
     * @return quantity of the cars.
     */

    Integer count();
}
