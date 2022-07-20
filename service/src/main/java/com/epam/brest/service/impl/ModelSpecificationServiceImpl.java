package com.epam.brest.service.impl;

import com.epam.brest.dao_api.ModelSpecificationDao;
import com.epam.brest.model.ModelSpecification;
import com.epam.brest.service_api.ModelSpecificationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ModelSpecificationServiceImpl implements ModelSpecificationService {

    /**
     * @Field modelSpecificationDao ModelSpecificationDao.
     */

    private final ModelSpecificationDao modelSpecificationDao;

    /**
     * @Constructor
     * @param modelSpecificationDao ModelSpecificationDao.
     */

    public ModelSpecificationServiceImpl(ModelSpecificationDao modelSpecificationDao) {
        this.modelSpecificationDao = modelSpecificationDao;
    }

    /**
     * Getting car's model specification by model name.
     *
     * @param carModel String;
     * @return ModelSpecification.
     */

    @Transactional(readOnly = true)
    @Override
    public ModelSpecification getModelSpecificationByCarModel(final String carModel) {
        return modelSpecificationDao.getModelSpecificationByCarModel(carModel);
    }
}
