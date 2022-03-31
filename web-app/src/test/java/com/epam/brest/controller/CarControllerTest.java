package com.epam.brest.controller;

import com.epam.brest.model.Car;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.net.URI;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class CarControllerTest {

    public static final Logger LOG = LogManager.getLogger(DriverControllerTest.class);

    public static final String CARS_URL = "http://localhost:8088/cars";

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockRestServiceServer;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void seyUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        mockRestServiceServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    void shouldReturnPageCars() throws Exception {
        LOG.info("Method shouldReturnPageCars() started of class {}", getClass().getName());

        Car carOne = createCar(1, "GAZ", 1);
        Car carTwo = createCar(2, "ZIL", 3);
        Car carThree = createCar(3, "LADA", 3);
        Car carFour = createCar(4, "GIGA", 1);
        Car carFive = createCar(5, "URAL", 3);

        mockRestServiceServer.expect(ExpectedCount.once(), requestTo(new URI(CARS_URL)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(objectMapper.writeValueAsString(Arrays.asList(carOne, carTwo, carThree, carFour, carFive)))
                );

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/cars")
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("cars/cars"))
                .andExpect(model().attribute("carList", hasItem(
                        allOf(
                                hasProperty("carId", is(carOne.getCarId())),
                                hasProperty("carModel", is(carOne.getCarModel())),
                                hasProperty("driverId", is(carOne.getDriverId()))
                        )
                )))
                .andExpect(model().attribute("carList", hasItem(
                        allOf(
                                hasProperty("carId", is(carTwo.getCarId())),
                                hasProperty("carModel", is(carTwo.getCarModel())),
                                hasProperty("driverId", is(carTwo.getDriverId()))
                        )
                )))
                .andExpect(model().attribute("carList", hasItem(
                        allOf(
                                hasProperty("carId", is(carThree.getCarId())),
                                hasProperty("carModel", is(carThree.getCarModel())),
                                hasProperty("driverId", is(carThree.getDriverId()))
                        )
                )))
                .andExpect(model().attribute("carList", hasItem(
                        allOf(
                                hasProperty("carId", is(carFour.getCarId())),
                                hasProperty("carModel", is(carFour.getCarModel())),
                                hasProperty("driverId", is(carFour.getDriverId()))
                        )
                )))
                .andExpect(model().attribute("carList", hasItem(
                        allOf(
                                hasProperty("carId", is(carFive.getCarId())),
                                hasProperty("carModel", is(carFive.getCarModel())),
                                hasProperty("driverId", is(carFive.getDriverId()))
                        )
                )));
        mockRestServiceServer.verify();
    }

    @Test
    void shouldShowFormAddingCars() throws Exception {
        LOG.info("Method shouldShowFormAddingCars() started of class {}", getClass().getName());

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/cars/new-car")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("cars/new-car"));
    }

    @Test
    void shouldSaveCar() throws Exception {
        LOG.info("Method shouldSaveCar() started of class {}", getClass().getName());
        // WHEN
        mockRestServiceServer.expect(ExpectedCount.once(), requestTo(new URI(CARS_URL)))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("1")
                );
        Car car = new Car( "GAZ", 1);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/cars")
                                .param("carModel", car.getCarModel())
                                .param("driverId", String.valueOf(car.getDriverId()))
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/cars"))
                .andExpect(redirectedUrl("/cars"));

        mockRestServiceServer.verify();
    }

    @Test
    void shouldFailAddCarOnEmptyName() throws Exception {
        LOG.info("Method shouldFailAddCarOnEmptyName() started of class {}", getClass().getName());
        // WHEN
        Car car = new Car("");
        // THEN
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/cars")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("carModel", car.getCarModel())
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("cars/new-car"))
                .andExpect(
                        model().attributeHasFieldErrors(
                                "car", "carModel"
                        )
                );
    }

    @Test
    void shouldFailAddCarOnEmptyDriverId() throws Exception {
        LOG.info("Method shouldFailAddCarOnEmptyDriverId() started of class {}", getClass().getName());
        // WHEN
        Car car = new Car("");
        // THEN
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/cars")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("driverId", String.valueOf(car.getDriverId()))
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("cars/new-car"))
                .andExpect(
                        model().attributeHasFieldErrors(
                                "car", "driverId"
                        )
                );
    }

    @Test
    void shouldUpdateCar() throws Exception {
        LOG.info("Method shouldUpdateCar() started of class {}", getClass().getName());
        Car carSrc = createCar(1, "UAZ", 2);
        mockRestServiceServer.expect(ExpectedCount.once(), requestTo(new URI(CARS_URL + "/" + carSrc.getCarId())))
                .andExpect(method(HttpMethod.PATCH))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("1")
                );
        carSrc.setCarModel(carSrc.getCarModel() + "_TEST");

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/cars/" + carSrc.getCarId())
                                .param("carModel", carSrc.getCarModel())
                                .param("driverId", String.valueOf(carSrc.getDriverId()))
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/cars"))
                .andExpect(redirectedUrl("/cars"));

        mockRestServiceServer.verify();
    }

    @Test
    void shouldFailUpdateCarOnEmptyName() throws Exception {
        LOG.info("Method shouldFailUpdateCarOnEmptyName() started of class {}", getClass().getName());
        // WHEN
        Car carSrc = createCar(2, "AUDI", 3);
        mockRestServiceServer.expect(ExpectedCount.once(), requestTo(new URI(CARS_URL + "/" + carSrc.getCarId())))
                .andExpect(method(HttpMethod.PATCH))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("1")
                );
        carSrc.setCarModel("");
        // THEN
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/cars/" + carSrc.getCarId())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("carModel", carSrc.getCarModel())
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("cars/update-car"))
                .andExpect(
                        model().attributeHasFieldErrors(
                                "car", "carModel"
                        )
                );
    }

    @Test
    void shouldFailUpdateCarOnEmptyDriverId() throws Exception {
        LOG.info("Method shouldFailUpdateCarOnEmptyDriverId() started of class {}", getClass().getName());
        // WHEN
        Car carSrc = createCar(2, "AUDI", 3);
        mockRestServiceServer.expect(ExpectedCount.once(), requestTo(new URI(CARS_URL + "/" + carSrc.getCarId())))
                .andExpect(method(HttpMethod.PATCH))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("1")
                );
        carSrc.setDriverId(null);
        // THEN
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/cars/" + carSrc.getCarId())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("driverId", String.valueOf(carSrc.getDriverId()))
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("cars/update-car"))
                .andExpect(
                        model().attributeHasFieldErrors(
                                "car", "driverId"
                        )
                );
    }

    @Test
    void shouldDeleteCar() throws Exception {
        LOG.info("Method shouldDeleteCar() started of class {}", getClass().getName());
        // WHEN
        Car carSrc = createCar(2, "AUDI", 3);
        mockRestServiceServer.expect(ExpectedCount.once(), requestTo(new URI(CARS_URL + "/" + carSrc.getCarId())))
                .andExpect(method(HttpMethod.DELETE))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("1")
                );
        //THEN
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/cars/" + carSrc.getCarId())
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/cars"))
                .andExpect(redirectedUrl("/cars"));
        mockRestServiceServer.verify();
    }

    private Car createCar(Integer carId, String model, Integer driverId) {
        Car car = new Car();
        car.setCarId(carId);
        car.setCarModel(model);
        car.setDriverId(driverId);
        return car;
    }
}
