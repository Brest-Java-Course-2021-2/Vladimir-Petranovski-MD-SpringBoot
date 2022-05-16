package com.epam.brest.rest.controller.dto;

import com.epam.brest.mongodb_postgresql.model.DriverDtoMongodb;
import com.epam.brest.mongodb_postgresql.service.DriverDtoMongodbService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@CrossOrigin
public class DriverDtoMongodbController {

    /**
     * Field driverDtoMongodbService DriverDtoMongodbService.
     */

    private final DriverDtoMongodbService driverDtoMongodbService;

    /**
     * @Constructor
     * @param driverDtoMongodbService DriverDtoMongodbService.
     */

    public DriverDtoMongodbController(DriverDtoMongodbService driverDtoMongodbService) {
        this.driverDtoMongodbService = driverDtoMongodbService;
    }

    /**
     * Find all drivers from MongoDB database.
     *
     * @return driver's list.
     */

    @GetMapping("/mongo")
    public ResponseEntity<?> findAllDriversFromMongo() {
        Collection<DriverDtoMongodb> drivers = driverDtoMongodbService.findAllDriversMongodb();
        return new ResponseEntity<>(drivers, HttpStatus.OK);
    }
}
