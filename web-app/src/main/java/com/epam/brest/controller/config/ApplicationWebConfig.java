package com.epam.brest.controller.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.ObjectInputValidation;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

@Configuration
@ComponentScan(basePackages = "com.epam.brest")
public class ApplicationWebConfig implements WebMvcConfigurer {

//    @Bean
//    public SimpleDateFormat simpleDateFormat() {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
//        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("PST"));
//        return simpleDateFormat;
//    }

//    public void configureMessageConverter(List<HttpMessageConverter<?>> converters) {
//        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
//                .modules(new JavaTimeModule(), new Jdk8Module()).build()
//                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
//        converters.add(new MappingJackson2HttpMessageConverter(objectMapper));
//    }

}
