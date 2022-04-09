package com.epam.brest.controller;

import com.epam.brest.controller.validator.DriverValidator;
import com.epam.brest.model.Driver;
import com.epam.brest.service_api.DriverService;
import com.epam.brest.service_api.dto.DriverDtoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class DriverController {

    public static final Logger LOG = LogManager.getLogger(
            DriverController.class);

    /**
     * Field driverDtoService.
     */

    private final DriverDtoService driverDtoService;

    /**
     * Field driverService.
     */

    private final DriverService driverService;

    /**
     * Field driverValidator.
     */

    private final DriverValidator driverValidator;

    /**
     * Constructor.
     *
     * @param driverDtoService driverDto Service.
     * @param driverService driverService.
     * @param driverValidator driverValidator.
     */

    public DriverController(final DriverDtoService driverDtoService,
                            final DriverService driverService,
                            final DriverValidator driverValidator) {
        this.driverDtoService = driverDtoService;
        this.driverService = driverService;
        this.driverValidator = driverValidator;
    }

    /**
     * Goto driver's list page.
     *
     * @param model model.
     * @return view name.
     */

    @GetMapping("/drivers_dto")
    public final String findAllDrivers(final Model model) {
        LOG.info("Method findAllDrivers() started of class {}",
                getClass().getName());
        model.addAttribute("driverList",
                driverDtoService.findAllDriverWithCountCars());
        return "drivers/drivers";
    }

    /**
     * Goto form adding new driver page.
     *
     * @param driver driver.
     * @return view name.
     */

    @GetMapping("/drivers/new-driver")
    public final String showFormAddingDriver(
            @ModelAttribute("driver") final Driver driver, final Model model) {
        LOG.info("Method showFormAddingDriver() "
                        + "with driver {} started of class {}",
                driver, getClass().getName());

        model.addAttribute("isNew", true);

        return "drivers/new-driver";
    }

    /**
     * Goto driver's list after adding new driver page.
     *
     * @param driver driver.
     * @param result for validate.
     * @return view name.
     */

    @PostMapping("/drivers_dto")
    public String saveDriver(@ModelAttribute("driver") final Driver driver,
                             final BindingResult result) {
        LOG.info("Method saveDriver() with driver {} started of class {}",
                driver, getClass().getName());

        driverValidator.validate(driver, result);
        if (result.hasErrors()) {
            return "drivers/new-driver";
        }

        driverService.saveDriver(driver);
        return "redirect:/drivers_dto";
    }

    /**
     * Goto form updating driver page.
     *
     * @param id driver for updating.
     * @param model model.
     * @return view name.
     */

    @GetMapping("/drivers/{id}/update-driver")
    public String showFormForUpdatingDriver(
            @PathVariable("id") final Integer id,
            final Model model) {
        LOG.info("Method showFormForUpdatingDriver()"
                        + " with id {} started of class {}",
                id, getClass().getName());

        model.addAttribute("isNew", false);
        model.addAttribute("driver",
                driverService.findDriverById(id));
        return "drivers/update-driver";
    }

    /**
     * Goto driver's list page after updating driver.
     *
     * @param driver driver.
     * @param result for validate.
     * @param id driver for updating.
     * @return view name.
     */

    @PostMapping("/drivers/{id}")
    public String updateDriver(@ModelAttribute("driver") final Driver driver,
                               final BindingResult result,
                               @PathVariable("id") final Integer id) {
        LOG.info("Method updateDriver() with driver {}"
                        + " and id {} started of class {}",
                driver, id, getClass().getName());

        driverValidator.validate(driver, result);
        if (result.hasErrors()) {
            return "drivers/update-driver";
        }
        driverService.updateDriverById(id, driver);
        return "redirect:/drivers_dto";
    }

    /**
     * Goto driver's list page after deleting driver.
     *
     * @param id driver for deleting.
     * @return view name.
     */

    @GetMapping("/drivers/{id}")
    public String deleteDriver(@PathVariable("id") final Integer id) {
        LOG.info("Method deleteDriver() with id {} started of class {}",
                id, getClass().getName());
        driverService.deleteDriverById(id);
        return "redirect:/drivers_dto";
    }

    /**
     * Goto choice form for start work date.
     *
//     * @param driverDto of DriverDto.
     * @return view name.
     */

    @GetMapping("/drivers_dto/form-range")
    public String showFormForChoseDateRange(final Model model) {
        LOG.info("Method showFormForChoseDateRange() started of class {}",
                getClass().getName());
        model.addAttribute("fromDateChoose", "");
        model.addAttribute("toDateChoose", "");
        return "drivers/form-range";
    }

    /**
     * Goto driver's list page after choice on start work date.
     *
//     * @param driverDto of DriverDto class.
     * @param model of Model class.
     * @return view name.
     */

    @GetMapping("/drivers_dto/drivers-range")
    public String showDriversListOnRange(
            @RequestParam(value = "fromDateChoose", required = false)
            final String fromDateChoose,
            @RequestParam(value = "toDateChoose", required = false)
            final String toDateChoose,
            final Model model) {
        LOG.info("Method showDriversListOnRange() started of class {}",
                getClass().getName());
        model.addAttribute("driverList",
                driverDtoService.chooseDriverOnDateRange(fromDateChoose,
                        toDateChoose));
        return "drivers/drivers-range";
    }
}
