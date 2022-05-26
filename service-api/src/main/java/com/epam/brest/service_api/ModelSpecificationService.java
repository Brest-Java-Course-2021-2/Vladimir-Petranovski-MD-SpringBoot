package com.epam.brest.service_api;

import com.epam.brest.model.ModelSpecification;

public interface ModelSpecificationService {

    /**
     * Getting car's model specification by model name.
     *
     * @param carModel String;
     * @return ModelSpecification.
     */

    ModelSpecification getModelSpecificationByCarModel(String carModel);
}
