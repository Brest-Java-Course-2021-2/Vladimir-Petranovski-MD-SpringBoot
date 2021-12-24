package com.epam.brest.controller;

import com.epam.brest.model.Driver;
import com.epam.brest.model.dto.DriverDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.net.URI;
import java.time.Instant;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Disabled
public class DriverControllerTest {

    public static final Logger LOG = LogManager.getLogger(DriverControllerTest.class);

    public static final String DRIVERS_DTO_URL = "http://localhost:8088/drivers_dto";
    public static final String DRIVERS_URL = "http://localhost:8088/drivers";

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockRestServiceServer;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        mockRestServiceServer = MockRestServiceServer.createServer(restTemplate);
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule()).registerModule(new Jdk8Module())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
                        false);
    }

    @Test
    void shouldFindAllDriverWithCountCars() throws Exception {
        LOG.info("Method shouldFindAllDriverWithCountCars() started {}",
                getClass().getName());

        DriverDto driverDto1 = createDriverDto(1, "VASIA", Instant.parse("1998-10-01T12:02:01.8472Z"), BigDecimal.valueOf(500), 2);
        DriverDto driverDto2 = createDriverDto(2, "VOVA", Instant.parse("2010-10-11T08:30:30.1234Z"), BigDecimal.valueOf(850), 0);
        DriverDto driverDto3 = createDriverDto(3, "VITALIY", Instant.parse("2005-04-28T14:44:50.5327Z"), BigDecimal.valueOf(650), 3);

        mockRestServiceServer.expect(ExpectedCount.once(), requestTo(new URI(DRIVERS_DTO_URL)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(objectMapper.writeValueAsString(Arrays.asList(driverDto1, driverDto2, driverDto3)))
                );
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/drivers_dto")
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("drivers/drivers"))
                .andExpect(model().attribute("driverList", hasItem(
                        allOf(
                                hasProperty("driverId", is(driverDto1.getDriverId())),
                                hasProperty("driverName", is(driverDto1.getDriverName())),
                                hasProperty("driverDateStartWork", is(driverDto1.getDriverDateStartWork())),
                                hasProperty("driverSalary", is(driverDto1.getDriverSalary())),
                                hasProperty("countOfCarsAssignedToDriver", is(driverDto1.getCountOfCarsAssignedToDriver()))
                        )
                ))).andExpect(model().attribute("driverList", hasItem(
                        allOf(
                                hasProperty("driverId", is(driverDto2.getDriverId())),
                                hasProperty("driverName", is(driverDto2.getDriverName())),
                                hasProperty("driverDateStartWork", is(driverDto2.getDriverDateStartWork())),
                                hasProperty("driverSalary", is(driverDto2.getDriverSalary())),
                                hasProperty("countOfCarsAssignedToDriver", is(driverDto2.getCountOfCarsAssignedToDriver()))
                        )
                ))).andExpect(model().attribute("driverList", hasItem(
                        allOf(
                                hasProperty("driverId", is(driverDto3.getDriverId())),
                                hasProperty("driverName", is(driverDto3.getDriverName())),
                                hasProperty("driverDateStartWork", is(driverDto3.getDriverDateStartWork())),
                                hasProperty("driverSalary", is(driverDto3.getDriverSalary())),
                                hasProperty("countOfCarsAssignedToDriver", is(driverDto3.getCountOfCarsAssignedToDriver()))
                        )
                )));
        mockRestServiceServer.verify();
    }

    @Test
    void shouldShowFormAddingDriver() throws Exception {
        LOG.info("Method showFormAddingDriver() started of class {}", getClass().getName());

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/drivers/new-driver")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("drivers/new-driver"));
    }

    @Test
    void shouldSaveDriver() throws Exception {
        LOG.info("Method shouldSaveDriver() started of class {}", getClass().getName());

        // WHEN
        mockRestServiceServer.expect(ExpectedCount.once(), requestTo(new URI(DRIVERS_DTO_URL)))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("1")
                );
        Driver driver = new Driver("SERGEY", Instant.parse("1999-10-01T12:02:01.8472Z"), BigDecimal.valueOf(740));
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/drivers_dto")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("driverName", driver.getDriverName())
                                .param("driverDateStartWork", String.valueOf(driver.getDriverDateStartWork()))
                                .param("driverSalary", String.valueOf(driver.getDriverSalary()))
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/drivers_dto"))
                .andExpect(redirectedUrl("/drivers_dto"));

        //when
        mockRestServiceServer.verify();
    }

    @Test
    void shouldFailAddDriverOnEmptyName() throws Exception {
        LOG.info("Method shouldFailAddDriverOnEmptyName() started of class {}", getClass().getName());

        // WHEN
        Driver driver = new Driver("");

        // THEN
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/drivers_dto")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("driverName", driver.getDriverName())
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("drivers/new-driver"))
                .andExpect(
                        model().attributeHasFieldErrors(
                                "driver", "driverName"
                        )
                );
    }

    @Test
    void shouldFailAddDriverOnEmptyDRiverDateStartWork() throws Exception {
        LOG.info("Method shouldFailAddDriverOnEmptyName() started of class {}", getClass().getName());
        // WHEN
        Driver driver = new Driver("TEST", null, new BigDecimal(300));
        // THEN
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/drivers_dto")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("driverName", driver.getDriverName())
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("drivers/new-driver"))
                .andExpect(
                        model().attributeHasFieldErrors(
                                "driver", "driverDateStartWork"
                        )
                );
    }

    @Test
    void shouldFailAddDriverOnEmptySalary() throws Exception {
        LOG.info("Method shouldFailAddDriverOnEmptySalary() started of class {}", getClass().getName());
        // WHEN
        Driver driver = new Driver("TEST", Instant.parse("1996-10-10T00:00:00.001Z"), null);
        // THEN
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/drivers_dto")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("driverSalary", String.valueOf(driver.getDriverSalary()))
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("drivers/new-driver"))
                .andExpect(
                        model().attributeHasFieldErrors(
                                "driver", "driverSalary"
                        )
                );
    }

    @Test
    void shouldUpdateDriver() throws Exception {
        LOG.info("Method shouldUpdateDriver() started of class {}", getClass().getName());
        Driver driver = createDriver(1, "VLADIMIR", Instant.parse("2001-01-01T00:00:01.00Z"), new BigDecimal(200));
        mockRestServiceServer.expect(ExpectedCount.once(), requestTo(new URI(DRIVERS_URL + "/" + driver.getDriverId())))
                .andExpect(method(HttpMethod.PATCH))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("1")
                );
        driver.setDriverName(driver.getDriverName() + "_TEST");

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/drivers/" + driver.getDriverId())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("driverName", driver.getDriverName())
                                .param("driverDateStartWork", String.valueOf(driver.getDriverDateStartWork()))
                                .param("driverSalary", String.valueOf(driver.getDriverSalary()))
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/drivers_dto"))
                .andExpect(redirectedUrl("/drivers_dto"));
        mockRestServiceServer.verify();
    }

    @Test
    void shouldFailUpdateDriverOnEmptyName() throws Exception {
        LOG.info("Method shouldFailUpdateDriverOnEmptyName() started of class {}", getClass().getName());
        // WHEN
        Driver driver = createDriver(1, "VLADIMIR", Instant.parse("2001-01-01T00:00:01.00Z"), new BigDecimal(200));

        mockRestServiceServer.expect(ExpectedCount.once(), requestTo(new URI(DRIVERS_URL + "/" + driver.getDriverId())))
                .andExpect(method(HttpMethod.PATCH))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("1")
                );
        driver.setDriverName("");

        // THEN
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/drivers/" + driver.getDriverId())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("driverName", driver.getDriverName())
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("drivers/update-driver"))
                .andExpect(
                        model().attributeHasFieldErrors(
                                "driver", "driverName"
                        )
                );
    }

    @Test
    void shouldFailUpdateDriverOnEmptyDriverDateStartWork() throws Exception {
        LOG.info("Method shouldFailUpdateDriverOnEmptyDriverDateStartWork() started of class {}", getClass().getName());
        // WHEN
        Driver driver = createDriver(1, "VLADIMIR", Instant.parse("2001-01-01T00:00:01.00Z"), new BigDecimal(200));

        mockRestServiceServer.expect(ExpectedCount.once(), requestTo(new URI(DRIVERS_URL + "/" + driver.getDriverId())))
                .andExpect(method(HttpMethod.PATCH))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("1")
                );
        driver.setDriverDateStartWork(null);
        // THEN
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/drivers/" + driver.getDriverId())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("driverDateStartWork", String.valueOf(driver.getDriverDateStartWork()))
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("drivers/update-driver"))
                .andExpect(
                        model().attributeHasFieldErrors(
                                "driver", "driverDateStartWork"
                        )
                );
    }

    @Test
    void shouldFailUpdateDriverOnEmptySalary() throws Exception {
        LOG.info("Method shouldFailUpdateDriverOnEmptyName() started of class {}", getClass().getName());
        // WHEN
        Driver driver = createDriver(1, "VLADIMIR", Instant.parse("2001-01-01T00:00:01.00Z"), new BigDecimal(200));

        mockRestServiceServer.expect(ExpectedCount.once(), requestTo(new URI(DRIVERS_URL + "/" + driver.getDriverId())))
                .andExpect(method(HttpMethod.PATCH))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("1")
                );
        driver.setDriverSalary(null);
        // THEN
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/drivers/" + driver.getDriverId())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("driverSalary", String.valueOf(driver.getDriverSalary()))
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("drivers/update-driver"))
                .andExpect(
                        model().attributeHasFieldErrors(
                                "driver", "driverSalary"
                        )
                );
    }

    @Test
    void shouldDeleteDriver() throws Exception {
        LOG.info("Method started: checkDeleteDriverById() of {}", getClass().getName());
        // WHEN
        Driver driver = createDriver(1, "VLADIMIR", Instant.parse("2001-01-01T00:00:01.00Z"), new BigDecimal(200));

        mockRestServiceServer.expect(ExpectedCount.once(), requestTo(new URI(DRIVERS_URL + "/" + driver.getDriverId() + "/delete-driver")))
                .andExpect(method(HttpMethod.DELETE))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("1")
                );
        // THEN
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/drivers/" + driver.getDriverId() + "/delete-driver")
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/drivers_dto"))
                .andExpect(redirectedUrl("/drivers_dto"));

        mockRestServiceServer.verify();
    }

    @Test
    void shouldReturnFormForChoosingDriversByDate() throws Exception {
        LOG.info("Method shouldReturnFormForChoosingDriversByDate() started of class {}", getClass().getName());

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/drivers_dto/form-range")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("drivers/form-range"));
    }

    @Test
    void shouldDoChooseDriverFromDateToDate() throws Exception {
        LOG.info("Method shouldDoChooseDriverFromDateToDate() started of class {}", getClass().getName());
        // WHEN
        DriverDto driverDto1 = createDriverDto(1, "VASIA", Instant.parse("1998-10-01T12:02:01.8472Z"), BigDecimal.valueOf(500), 2);
        DriverDto driverDto2 = createDriverDto(2, "VOVA", Instant.parse("2010-10-11T08:30:30.1234Z"), BigDecimal.valueOf(850), 0);
        DriverDto driverDto3 = createDriverDto(3, "VITALIY", Instant.parse("2005-04-28T14:44:50.5327Z"), BigDecimal.valueOf(650), 3);

        mockRestServiceServer.expect(ExpectedCount.once(), requestTo(new URI(DRIVERS_DTO_URL + "/drivers-range?fromDateChoose&toDateChoose")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(objectMapper.writeValueAsString(Arrays.asList(driverDto1, driverDto2, driverDto3)))
                );
        // THEN
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/drivers_dto/drivers-range?fromDateChoose&toDateChoose")
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("drivers/drivers-range"))
                .andExpect(model().attribute("driverList", hasItem(
                        allOf(
                                hasProperty("driverId", is(driverDto1.getDriverId())),
                                hasProperty("driverName", is(driverDto1.getDriverName())),
                                hasProperty("driverDateStartWork", is(driverDto1.getDriverDateStartWork())),
                                hasProperty("driverSalary", is(driverDto1.getDriverSalary()))
//                                hasProperty("countOfCarsAssignedToDriver", is(driverDto1.getCountOfCarsAssignedToDriver()))
                        )
                ))).andExpect(model().attribute("driverList", hasItem(
                        allOf(
                                hasProperty("driverId", is(driverDto2.getDriverId())),
                                hasProperty("driverName", is(driverDto2.getDriverName())),
                                hasProperty("driverDateStartWork", is(driverDto2.getDriverDateStartWork())),
                                hasProperty("driverSalary", is(driverDto2.getDriverSalary()))
//                                hasProperty("countOfCarsAssignedToDriver", is(driverDto2.getCountOfCarsAssignedToDriver()))
                        )
                ))).andExpect(model().attribute("driverList", hasItem(
                        allOf(
                                hasProperty("driverId", is(driverDto3.getDriverId())),
                                hasProperty("driverName", is(driverDto3.getDriverName())),
                                hasProperty("driverDateStartWork", is(driverDto3.getDriverDateStartWork())),
                                hasProperty("driverSalary", is(driverDto3.getDriverSalary()))
//                                hasProperty("countOfCarsAssignedToDriver", is(driverDto3.getCountOfCarsAssignedToDriver()))
                        )
                )));
        mockRestServiceServer.verify();
    }

    private DriverDto createDriverDto(int driverId, String name, Instant dateStartWork, BigDecimal salary, Integer countCar) {
        DriverDto driver = new DriverDto();
        driver.setDriverId(driverId);
        driver.setDriverName(name);
        driver.setDriverDateStartWork(dateStartWork);
        driver.setDriverSalary(salary);
        driver.setCountOfCarsAssignedToDriver(countCar);
        return driver;
    }

    private Driver createDriver(int id, String name, Instant dateStartWork, BigDecimal salary) {
        Driver driver = new Driver();
        driver.setDriverId(id);
        driver.setDriverName(name);
        driver.setDriverDateStartWork(dateStartWork);
        driver.setDriverSalary(salary);
        return driver;
    }
}
