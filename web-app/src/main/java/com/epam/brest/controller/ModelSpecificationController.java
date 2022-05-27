package com.epam.brest.controller;

import com.epam.brest.service_api.ModelSpecificationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ModelSpecificationController {

    public static final Logger LOG = LogManager.getLogger(
            ModelSpecificationController.class);

    /**
     * @Field modelSpecificationService ModelSpecificationService.
     */

    private final ModelSpecificationService modelSpecificationService;

    /**
     * @Constructor
     * @param modelSpecificationService ModelSpecificationService
     */

    public ModelSpecificationController(
            final ModelSpecificationService modelSpecificationService) {
        this.modelSpecificationService = modelSpecificationService;
    }

    /**
     * Get specification of car by it name.
     *
     * @param carModel String.
     * @param model Model.
     * @return view String.
     */

    @GetMapping("/model_info/{model}")
    public String getModelSpecificationByCarName(
            @PathVariable("model") final String carModel, final Model model) {
        LOG.info("Method getModelSpecificationByCarName() started in class {}",
                getClass().getName());

        model.addAttribute("specification",
                modelSpecificationService.getModelSpecificationByCarModel(carModel));

        return "cars/cars";
    }
}
