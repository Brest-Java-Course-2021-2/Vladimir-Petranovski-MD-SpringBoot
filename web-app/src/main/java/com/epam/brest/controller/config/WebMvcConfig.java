package com.epam.brest.controller.config;

import com.epam.brest.mongodb_postgresql.service.DriverDtoMongodbService;
import com.epam.brest.service_api.CarService;
import com.epam.brest.service_api.DriverService;
import com.epam.brest.service_api.ModelSpecificationService;
import com.epam.brest.service_api.dto.DriverDtoService;
import com.epam.brest.service_rest.service.CarServiceRest;
import com.epam.brest.service_rest.service.DriverServiceRest;
import com.epam.brest.service_rest.service.ModelSpecificationServiceRest;
import com.epam.brest.service_rest.service.dto.DriverDtoMongodbServiceRest;
import com.epam.brest.service_rest.service.dto.DriverDtoServiceRest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan
public class WebMvcConfig {

    /**
     * @serialField protocol String.
     */

    @Value("${rest.server.protocol}")
    private String protocol;

    /**
     * @serialField host String.
     */

    @Value("${rest.server.host}")
    private String host;

    /**
     * @serialField port String.
     */

    @Value("${rest.server.port}")
    private Integer port;

    /**
     * RestTemplate's bean.
     *
     * @return restTemplate RestTemplate.
     */

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory());
    }

    /**
     * DriverDtoService's bean.
     *
     * @return driverDtoService DriverDtoService.
     */

    @Bean
    DriverDtoService driverDtoService() {
        String url = String.format("%s://%s:%d/drivers_dto", protocol, host, port);
        return new DriverDtoServiceRest(url, restTemplate());
    }

    /**
     * DriverService's bean.
     *
     * @return driverService DriverService.
     */

    @Bean
    DriverService driverService() {
        String url = String.format("%s://%s:%d/drivers", protocol, host, port);
        return new DriverServiceRest(url, restTemplate());
    }

    /**
     * CarService's bean.
     *
     * @return carService CarService.
     */

    @Bean
    CarService carService() {
        String url = String.format("%s://%s:%d/cars", protocol, host, port);
        return new CarServiceRest(url, restTemplate());
    }

    /**
     * DriverDtoMongodbService's bean.
     *
     * @return driverDtoMongodbService DriverDtoMongodbService.
     */

    @Bean
    DriverDtoMongodbService driverDtoMongodbService() {
        String url = String.format("%s://%s:%d/mongo", protocol, host, port);
        return new DriverDtoMongodbServiceRest(url, restTemplate());
    }

    /**
     * ModelSpecificationService's bean.
     *
     * @return modelSpecificationService ModelSpecificationService.
     */

    @Bean
    ModelSpecificationService modelSpecificationService() {
        String url = String.format("%s://%s:%d/model_info", protocol, host, port);
        return new ModelSpecificationServiceRest(url, restTemplate());
    }
}
