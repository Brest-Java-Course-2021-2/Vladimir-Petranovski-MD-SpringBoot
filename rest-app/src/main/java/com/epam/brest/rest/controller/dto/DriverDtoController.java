package com.epam.brest.rest.controller.dto;

import com.epam.brest.model.dto.DriverDto;
import com.epam.brest.service_api.dto.DriverDtoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin
@RequestMapping("/drivers_dto")
@Tag(name = "driver-dto-controller",
        description = "Allows to choose all drivers with assigning cars"
                + " and sort on start work date")
public class DriverDtoController {

    public static final Logger LOG = LogManager.getLogger(DriverDtoController.class);

    /**
     * Field driverDtoService.
     */

    private final DriverDtoService driverDtoService;

    /**
     * Constructor.
     *
     * @param driverDtoService driverDtoService.
     */

    public DriverDtoController(
            final DriverDtoService driverDtoService) {
        this.driverDtoService = driverDtoService;
    }

    /**
     * Fid driver's list Dto.
     *
     * @return Driver Dto collection in json format.
     */

    @Operation(summary = "Allows to get list of all drivers"
            + " with a amount of assigning cars")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Everything's OK",
                    content = @Content),
            @ApiResponse(responseCode = "404", description =
                    "Trying to get a non-existent list of drivers", content = @Content)})
    @GetMapping()
    public final Collection<DriverDto> findAllDriversWithCountCars() {
        LOG.info("Method findAllDriversWithCountCars() started of class {}",
                getClass().getName());
        return driverDtoService.findAllDriverWithCountCars();
    }

    /**
     * Fid driver's list Dto from date to date.
     *
     * @return Driver Dto collection in json format.
     */

    @Operation(summary = "Allows to get list of all drivers within start work dates")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Everything's OK",
                    content = @Content),
            @ApiResponse(responseCode = "404", description =
                    "Trying to get a non-existent list of drivers", content = @Content)})
    @GetMapping("/drivers-range")
    public Collection<DriverDto> showDriversListOnRange(
            @ModelAttribute final DriverDto driverDto) {
        LOG.info("Method showDriversListOnRange() started of class {} - {}",
                driverDto.getFromDateChoose(), driverDto.getToDateChoose());
        return driverDtoService.chooseDriverOnDateRange(driverDto.getFromDateChoose(),
                driverDto.getToDateChoose());
    }
}
