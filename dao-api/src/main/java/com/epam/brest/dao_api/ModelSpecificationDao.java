package com.epam.brest.dao_api;

import com.epam.brest.model.ModelSpecification;

public interface ModelSpecificationDao {

    /**
     * Getting car's model specification by model name.
     *
     * @param carModel String;
     * @return ModelSpecification.
     */

    ModelSpecification getModelSpecificationByCarModel(String carModel);
}
