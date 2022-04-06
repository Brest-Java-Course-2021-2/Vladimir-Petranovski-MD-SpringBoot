package com.epam.brest.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "version-controller", description =
        "Allows to know version application")
public class VersionController {

    public static final Logger LOG = LogManager.getLogger(
            VersionController.class);

    /**
     * Field VERSION.
     */

    private static final String VERSION = "0.0.1";

    /**
     * Goto version's page.
     *
     * @return local variable VERSION.
     */

    @Operation(description = "")
    @GetMapping(value = "/version")
    public String version() {
        LOG.info("Method version() started of class {}",
            getClass().getName());
        return VERSION;
    }
}
