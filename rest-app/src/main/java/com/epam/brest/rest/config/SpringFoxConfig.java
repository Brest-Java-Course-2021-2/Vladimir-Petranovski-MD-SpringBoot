package com.epam.brest.rest.config;

import com.epam.brest.rest.controller.VersionController;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;

@Configuration
public class SpringFoxConfig {

    /**
     * @bean configure swagger openApi.
     *
     * @return Docket class.
     */

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo());
    }

    /**
     * Set a describing of openApi of swagger's page.
     *
     * @return ApiInfo class.
     */

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "MOTOR DEPOT API",
                "Motor depot is a web application that allows to manage car park.",
                new VersionController().version(),
                "Here are terms of service.",
                new Contact("Vladimir Petranouski", "www.example.com", "vpetranovskij@gmail.com"),
                "Apache 2.0", "https://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
    }
}
