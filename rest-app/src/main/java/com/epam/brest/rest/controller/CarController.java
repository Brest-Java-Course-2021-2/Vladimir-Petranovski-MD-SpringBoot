package com.epam.brest.rest.controller;

import com.epam.brest.model.Car;
import com.epam.brest.service_api.CarService;
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

@RestController
@CrossOrigin
@RequestMapping("/cars")
@Tag(name = "car-controller", description = "Allows to manage cars of motor depot")
public class CarController {

    public static final Logger LOG = LogManager.getLogger(
            CarController.class);

    /**
     * Field carService.
     */

    private final CarService carService;

    /**
     * Constructor.
     *
     * @param carService carService.
     */

    public CarController(
            final CarService carService) {
        this.carService = carService;
    }

    /**
     * Goto car's list.
     *
     * @return car's list in json.
     */

    @Operation(summary = "Allows to get list of all cars")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Everything's OK",
                    content = @Content),
            @ApiResponse(responseCode = "404", description =
                    "Trying to get a non-existent list of cars", content = @Content)})
    @GetMapping()
    public final Collection<Car> findAllCars() {
        LOG.info("Method findAllCars() started of class {}",
                getClass().getName());
        return carService.findAllCars();
    }

    /**
     * Goto car by id.
     *
     * @param id car.
     * @return car in json.
     */

    @Operation(summary = "Allows to get car by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Everything's OK",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Something wrong! Bad request!",
                    content = @Content),
            @ApiResponse(responseCode = "404", description =
                    "Trying to get a non-existent car", content = @Content)})
    @GetMapping(value = "/{id}")
    public final Car findCarById(@PathVariable final Integer id) {
        LOG.info("Method findCarById() with id: {} started of class {}",
                id, getClass().getName());

        return carService.findCarById(id);
    }

    /**
     * Persist car.
     *
     * @param car car.
     * @return 200 ok.
     */

    @Operation(summary = "Allows to create car")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Everything's OK",
                    content = @Content),
            @ApiResponse(responseCode = "201", description =
                    "The car was created", content = @Content),
            @ApiResponse(responseCode = "500", description =
                    "Something wrong! We'll sort this out soon.")})
    @PostMapping(consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<Integer> saveCar(@RequestBody final Car car) {
        LOG.info("Method saveCar() with car: {} started of class {}",
                car, getClass().getName());

        Integer id = carService.saveCar(car);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    /**
     * Update car.
     *
     * @param car car.
     * @param id car.
     * @return 200 ok.
     */

    @Operation(summary = "Allows to update car by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Everything's OK",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Something wrong! Bad request!",
                    content = @Content),
            @ApiResponse(responseCode = "404", description =
                    "Trying to update a non-existent car", content = @Content),
            @ApiResponse(responseCode = "500", description =
                    "Something wrong! We'll sort this out soon.")})
    @PatchMapping(value = "/{id}", consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<Integer> updateCar(@RequestBody final Car car,
                                             @PathVariable final Integer id) {
        LOG.info("Method updateCar() with car: {} started of class {}",
                car, getClass().getName());

        int result = carService.updateCarById(id, car);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Delete car.
     *
     * @param id car.
     * @return 200 ok.
     */

    @Operation(summary = "Allows to delete car by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Everything's OK",
                    content = @Content),
            @ApiResponse(responseCode = "204", description = "Everything's OK to continue",
                    content = @Content),
            @ApiResponse(responseCode = "302", description = "You were redirected"
                    + " to page with a list of all cars",
                    content = @Content),
            @ApiResponse(responseCode = "404", description =
                    "Trying to delete a non-existent car", content = @Content),
            @ApiResponse(responseCode = "500", description =
                    "Something wrong! We'll sort this out soon.")})
    @DeleteMapping(value = "/{id}",
            produces = "application/json")
    public ResponseEntity<Integer> deleteCar(
            @PathVariable final Integer id) {
        LOG.info("Method updateCar() with id: {} started of class {}",
                id, getClass().getName());

        int result = carService.deleteCarById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
