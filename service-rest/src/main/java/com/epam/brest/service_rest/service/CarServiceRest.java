package com.epam.brest.service_rest.service;

import com.epam.brest.model.Car;
import com.epam.brest.service_api.CarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CarServiceRest implements CarService {

    public static final Logger LOG = LogManager.getLogger(
            CarServiceRest.class);

    /**
     * @serialField  url String.
     */

    private String url;

    /**
     * @serialField  restTemplate RestTemplate.
     */

    private RestTemplate restTemplate;

    /**
     * Constructor without parameters.
     */

    public CarServiceRest() {
    }

    /**
     * Constructor.
     *
     * @param enterUrl String.
     * @param enterRestTemplate RestTemplate.
     */

    public CarServiceRest(final String enterUrl,
                          final RestTemplate enterRestTemplate) {
        this.url = enterUrl;
        this.restTemplate = enterRestTemplate;
    }

    /**
     * Find all cars.
     *
     * @return list of cars.
     */

    @Override
    public List<Car> findAllCars() {
        LOG.info("Method findAllCars() started of class {}",
                getClass().getName());
        ParameterizedTypeReference<List<Car>> typeReference =
                new ParameterizedTypeReference<>() {};
        ResponseEntity<List<Car>> responseEntity = restTemplate
                .exchange(url, HttpMethod.GET, null, typeReference);
        return responseEntity.getBody();
    }

    /**
     * Find car by id.
     *
     * @param id car Id.
     * @return car by id.
     */

    @Override
    public Car findCarById(final Integer id) {
        LOG.info("Method findCarById()"
                        + " with id: {} started of class {}",
                id, getClass().getName());
        ResponseEntity<Car> responseEntity =
                restTemplate.getForEntity(url + "/" + id,
                        Car.class);
        return responseEntity.getBody();
    }

    /**
     * Persist new car.
     *
     * @param car car.
     * @return persisted car id.
     */

    @Override
    public Integer saveCar(final Car car) {
        LOG.info("Method saveDriver()"
                        + " with driver: {} started of class {}",
                car, getClass().getName());
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<Car> entity = new HttpEntity<>(car, headers);
        ResponseEntity<Integer> responseEntity = restTemplate.exchange(
                url, HttpMethod.POST, entity, Integer.class);
        return responseEntity.getBody();
    }

    /**
     * Update car.
     *
     * @param id  car id.
     * @param car car.
     * @return number of updated records in the database.
     */

    @Override
    public Integer updateCarById(final Integer id, final Car car) {
        LOG.info("Method updateCarById()"
                        + " with id {} and with driver: {} started of class {}",
                id, car, getClass().getName());
        // restTemplate.put(url, car) // may be mistakes
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Car> entity = new HttpEntity<>(car, headers);
        ResponseEntity<Integer> result = restTemplate.exchange(
                url + "/" + id, HttpMethod.PATCH, entity, Integer.class);
        return result.getBody();
    }

    /**
     * Delete car by id.
     *
     * @param id car.
     * @return number of updated records in the database.
     */

    @Override
    public Integer deleteCarById(final Integer id) {
        LOG.info("Method deleteCarById()"
                        + " with id: {} started of class {}",
                id, getClass().getName());
        // restTemplate.delete(url, car) // may be mistakes
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Car> entity = new HttpEntity<>(headers);
        ResponseEntity<Integer> result = restTemplate.exchange(
                url + "/" + id, HttpMethod.DELETE,
                entity, Integer.class);
        return result.getBody();
    }

    /**
     * Count cars.
     *
     * @return quantity of the cars.
     */

    @Override
    public Integer count() {
        LOG.info("Method count() started of class {}",
                getClass().getName());
        ResponseEntity<Integer> responseEntity =
                restTemplate.getForEntity(url + "/count", Integer.class);
        return responseEntity.getBody();
    }
}
