package com.epam.brest.rest.controller;

import com.epam.brest.model.Driver;
import com.epam.brest.service_api.DriverService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Tag(name = "driver-controller", description = "Allows to manage drivers of motor depot")
@RestController
@CrossOrigin
public class DriverController {

    public static final Logger LOG = LogManager.getLogger(
            DriverController.class);

    /**
     * Field driverService.
     */

    private final DriverService driverService;

    /**
     * Constructor.
     *
     * @param  driverService DriverService.
     */

    public DriverController(
            final DriverService driverService) {
        this.driverService = driverService;
    }

    /**
     * Goto driver's list.
     *
     * @return driver's list in json.
     */

    @Operation(summary = "Allows to get list of all drivers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Everything's OK",
                    content = @Content),
            @ApiResponse(responseCode = "404", description =
                    "Trying to get a non-existent list of drivers", content = @Content)})
    @GetMapping("/drivers")
    public Collection<Driver> findAllDrivers() {
        LOG.info("Method findAllDrivers() started of class {}",
                getClass().getName());
        return driverService.findAllDrivers();
    }

    /**
     * Goto driver by id.
     *
     * @param id driver.
     * @return driver in json.
     */

    @Operation(summary = "Allows to get driver by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Everything's OK",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Something wrong! Bad request!",
                    content = @Content),
            @ApiResponse(responseCode = "404", description =
                    "Trying to get a non-existent driver", content = @Content)})
    @GetMapping(value = "/drivers/{id}")
    public final Driver findDriverById(@PathVariable("id") final Integer id) {
        LOG.info("Method findDriverById() with id {} started of class {}",
                id, getClass().getName());

        return driverService.findDriverById(id);
    }

    /**
     * Persist driver.
     *
     * @param driver driver.
     * @return 200 ok.
     */

    @Operation(summary = "Allows to create driver")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Everything's OK",
                    content = @Content),
            @ApiResponse(responseCode = "201", description =
                    "The driver was created", content = @Content),
            @ApiResponse(responseCode = "500", description =
                    "Something wrong! We'll sort this out soon.")})
    @PostMapping(value = "/drivers_dto", consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<Integer> saveDriver(
            @RequestBody final Driver driver) {
        LOG.info("Method findDriverById() with driver {} started of class {}",
                driver, getClass().getName());

        Integer id = driverService.saveDriver(driver);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    /**
     * Update driver.
     *
     * @param driver driver.
     * @param id driver.
     * @return 200 ok.
     */

    @Operation(summary = "Allows to update driver by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Everything's OK",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Something wrong! Bad request!",
                    content = @Content),
            @ApiResponse(responseCode = "404", description =
                    "Trying to update a non-existent driver", content = @Content),
            @ApiResponse(responseCode = "500", description =
                    "Something wrong! We'll sort this out soon.")})
    @PatchMapping(value = "/drivers/{id}", consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<Integer> updateDriver(
            @RequestBody final Driver driver,
            @PathVariable final Integer id) {
        LOG.info("Method updateDriver() with id: {}"
                        + " and driver: {} started of class {}",
                id, driver, getClass().getName());

        Integer quantity = driverService.updateDriverById(id, driver);
        return new ResponseEntity<>(quantity, HttpStatus.OK);
    }

    /**
     * Delete driver.
     *
     * @param id driver.
     * @return 200 ok.
     */

    @Operation(summary = "Allows to delete driver by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Everything's OK",
                    content = @Content),
            @ApiResponse(responseCode = "204", description = "Everything's OK to continue",
                    content = @Content),
            @ApiResponse(responseCode = "302", description = "You were redirected"
                    + " to page with a list of all drivers",
                    content = @Content),
            @ApiResponse(responseCode = "404", description =
                    "Trying to delete a non-existent driver", content = @Content),
            @ApiResponse(responseCode = "500", description =
                    "Something wrong! We'll sort this out soon.")})
    @DeleteMapping(value = "/drivers/{id}",
            produces = "application/json")
    public ResponseEntity<Integer> deleteDriverById(
            @PathVariable final Integer id) {
        LOG.info("Method deleteDriverById() with id: {} started of class {}",
                id, getClass().getName());

        Integer quantity = driverService.deleteDriverById(id);
        return new ResponseEntity<>(quantity, HttpStatus.OK);
    }
}
