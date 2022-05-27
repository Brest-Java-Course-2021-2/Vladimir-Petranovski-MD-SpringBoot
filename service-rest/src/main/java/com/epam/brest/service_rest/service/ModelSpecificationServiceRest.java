package com.epam.brest.service_rest.service;

import com.epam.brest.model.ModelSpecification;
import com.epam.brest.service_api.ModelSpecificationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ModelSpecificationServiceRest implements ModelSpecificationService {

    public static final Logger LOG = LogManager.getLogger(
            DriverServiceRest.class);

    /**
     * Field url String.
     */

    private String url;

    /**
     * Field restTemplate RestTemplate.
     */

    private RestTemplate restTemplate;

    /**
     * Constructor without parameters.
     */

    public ModelSpecificationServiceRest() {
    }

    /**
     * @Constructor
     *
     * @param url String.
     * @param restTemplate RestTemplate.
     */

    public ModelSpecificationServiceRest(final String url,
                                         final RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public ModelSpecification getModelSpecificationByCarModel(String carModel) {
        LOG.info("Method getModelSpecificationByCarModel() with parameter {} started {}",
                carModel, getClass().getName());
        ParameterizedTypeReference<ModelSpecification> typeReference =
                new ParameterizedTypeReference<>() {};

        ResponseEntity<ModelSpecification> responseEntity = restTemplate
                .exchange(url + "/" + carModel, HttpMethod.GET, null, typeReference);
        return responseEntity.getBody();
    }
}
