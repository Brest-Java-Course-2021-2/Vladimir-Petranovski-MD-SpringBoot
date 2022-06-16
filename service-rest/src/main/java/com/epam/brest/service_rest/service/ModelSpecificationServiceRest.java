package com.epam.brest.service_rest.service;

import com.epam.brest.model.ModelSpecification;
import com.epam.brest.service_api.ModelSpecificationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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
    public ModelSpecification getModelSpecificationByCarModel(final String carModel) {
        LOG.info("Method getModelSpecificationByCarModel() with parameter {} started {}",
                carModel, getClass().getName());
//        ParameterizedTypeReference<ModelSpecification> typeReference =
//                new ParameterizedTypeReference<>() {};
//
//        ResponseEntity<ModelSpecification> responseEntity = restTemplate
//                .exchange(url + "/" + carModel, HttpMethod.GET, null, typeReference);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(carModel, headers);
        ResponseEntity<ModelSpecification> responseEntity = restTemplate.exchange(
                url + "/" + carModel, HttpMethod.GET, entity, ModelSpecification.class);
        return responseEntity.getBody();
    }
}
