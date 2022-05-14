package com.epam.brest.mongodb_postgresql.service;

import com.epam.brest.mongodb_postgresql.model.DriverDtoMongodb;

import java.util.List;

public interface DriverDtoMongodbService {

    /**
     * Find all drivers.
     *
     * @return list of drivers.
     */

    List<DriverDtoMongodb> findAllDriversMongodb();
}
