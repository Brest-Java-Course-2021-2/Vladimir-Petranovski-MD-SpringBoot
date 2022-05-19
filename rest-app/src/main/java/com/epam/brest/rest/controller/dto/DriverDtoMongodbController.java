package com.epam.brest.rest.controller.dto;

import com.epam.brest.mongodb_postgresql.model.DriverDtoMongodb;
import com.epam.brest.mongodb_postgresql.service.DriverDtoMongodbService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@CrossOrigin
@Tag(name = "driver-dto-mongodb-controller",
        description = "Allows to get all drivers from MongoDB database")
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

    @Operation(summary = "Allows to get list of all drivers"
            + " from MongoDB database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Driver's list was provide",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = DriverDtoMongodb.class)))}),
            @ApiResponse(responseCode = "404", description =
                    "Trying to get a non-existent list of drivers",
                    content = @Content),
            @ApiResponse(responseCode = "500", description =
                    "Something is wrong! We'll sort this out soon.",
                    content = @Content)})
    @GetMapping("/mongo")
    public ResponseEntity<?> findAllDriversFromMongo() {
        Collection<DriverDtoMongodb> drivers = driverDtoMongodbService.findAllDriversMongodb();
        return new ResponseEntity<>(drivers, HttpStatus.OK);
    }
}
