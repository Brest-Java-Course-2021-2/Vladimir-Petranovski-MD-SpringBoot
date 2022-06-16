package com.epam.brest.rest.controller;

import com.epam.brest.model.ModelSpecification;
import com.epam.brest.rest.config.CacheModelSpecificationWithGuavaConfig;
import com.epam.brest.service_api.ModelSpecificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@Tag(name = "model-specification-controller",
        description = "Allows to get description of model specification")
public class ModelSpecificationController {

    public static final Logger LOG = LogManager.getLogger(ModelSpecificationController.class);

    /**
     * @Field modelSpecificationService ModelSpecificationService.
     */

    private final ModelSpecificationService modelSpecificationService;

    /**
     * @Field cacheModelSpecification CacheModelSpecificationWithGuavaConfig.
     */

    private final CacheModelSpecificationWithGuavaConfig cacheModelSpecification;

    /**
     * @param modelSpecificationService ModelSpecificationService.
     * @param cacheModelSpecification CacheModelSpecificationWithGuavaConfig.
     * @Constructor
     */

    public ModelSpecificationController(
            final ModelSpecificationService modelSpecificationService,
            final CacheModelSpecificationWithGuavaConfig cacheModelSpecification) {
        this.modelSpecificationService = modelSpecificationService;
        this.cacheModelSpecification = cacheModelSpecification;

    }
    @Operation(summary = "Allows to get car's specification by its model")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specification was provided",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ModelSpecification.class)))}),
            @ApiResponse(responseCode = "404", description =
                    "Trying to get a non-existent model",
                    content = @Content),
            @ApiResponse(responseCode = "500", description =
                    "Something is wrong! We'll sort this out soon.",
                    content = @Content)})
    @GetMapping(value = "/model_info/{model}")
    public ResponseEntity<ModelSpecification> getModelSpecificationByCarModel(
            @PathVariable("model") @Parameter(description = "Car's model",
                    example = "URAL") final String carModel) {

        LOG.info("Method getModelSpecificationByCarModel() with car's model {} started of class {}",
                carModel, getClass().getName());


        ModelSpecification modelSpecification =
                modelSpecificationService.getModelSpecificationByCarModel(carModel);

        cacheModelSpecification.cacheRun(carModel);
        LOG.warn("{}", cacheModelSpecification.getCacheStats().toString());

        return new ResponseEntity<>(modelSpecification, HttpStatus.OK);
    }
}
