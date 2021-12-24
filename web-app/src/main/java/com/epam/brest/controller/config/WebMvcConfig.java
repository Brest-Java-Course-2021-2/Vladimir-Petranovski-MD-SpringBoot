package com.epam.brest.controller.config;

import com.epam.brest.service_api.CarService;
import com.epam.brest.service_api.DriverService;
import com.epam.brest.service_api.dto.DriverDtoService;
import com.epam.brest.service_rest.service.CarServiceRest;
import com.epam.brest.service_rest.service.DriverServiceRest;
import com.epam.brest.service_rest.service.dto.DriverDtoServiceRest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

@Configuration
@ComponentScan
public class WebMvcConfig implements WebMvcConfigurer {

    public static final Logger LOG = LogManager.getLogger(
            WebMvcConfig.class);

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
     * Configuration converter of messages.
     *
     * @param converters List<HttpMessageConverter<?>>.
     */

    @Override
    public void configureMessageConverters(
            final List<HttpMessageConverter<?>> converters) {

        LOG.info("Method configureMessageConverters() started in {}",
                getClass().getName());

        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
                .modules(new JavaTimeModule(), new Jdk8Module()).build()
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
                        false);
        converters.add(new MappingJackson2HttpMessageConverter(objectMapper));
    }

    /**
     * ViewResolver's bean.
     *
     * @return viewResolver ViewResolver.
     */

    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resourceViewResolver =
                new InternalResourceViewResolver();
        resourceViewResolver.setPrefix("/templates/");
        resourceViewResolver.setSuffix(".html");
        return resourceViewResolver;
    }
}
