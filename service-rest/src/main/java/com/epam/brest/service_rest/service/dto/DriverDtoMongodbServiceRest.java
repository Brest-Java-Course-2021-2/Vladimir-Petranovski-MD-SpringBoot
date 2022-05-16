package com.epam.brest.service_rest.service.dto;

import com.epam.brest.mongodb_postgresql.model.DriverDtoMongodb;
import com.epam.brest.mongodb_postgresql.service.DriverDtoMongodbService;
import org.apache.logging.log4j.LogManager;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import org.apache.logging.log4j.Logger;
import org.springframework.web.client.RestTemplate;

@Service
public class DriverDtoMongodbServiceRest implements DriverDtoMongodbService {

    private static final Logger LOG = LogManager.getLogger(
            DriverDtoMongodbServiceRest.class);

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

    public DriverDtoMongodbServiceRest() {
    }

    /**
     * Constructor.
     *
     * @param enterUrl          String.
     * @param enterRestTemplate RestTemplate.
     */

    public DriverDtoMongodbServiceRest(final String enterUrl,
                                       final RestTemplate enterRestTemplate) {
        this.url = enterUrl;
        this.restTemplate = enterRestTemplate;
    }

    /**
     * Find driverMongodb's list rest Dto.
     *
     * @return driverMongodb's list rest Dto.
     */

    @Override
    public List<DriverDtoMongodb> findAllDriversMongodb() {
        LOG.info("Method findAllDriversMongodb() started {}",
                getClass().getName());

        ParameterizedTypeReference<List<DriverDtoMongodb>> typeReference =
                new ParameterizedTypeReference<>() {};
        ResponseEntity<List<DriverDtoMongodb>> drivers =
                restTemplate.exchange(url, HttpMethod.GET, null, typeReference);
        return drivers.getBody();
    }
}
