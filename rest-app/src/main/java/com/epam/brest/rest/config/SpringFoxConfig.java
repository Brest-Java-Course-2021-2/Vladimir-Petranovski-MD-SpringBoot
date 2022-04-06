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

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.GET, List.of(
                        new ResponseBuilder().code("200")
                                .description("Very Good!!!").build(),
                        new ResponseBuilder().code("400")
                                .description("Bad Request!!!").build(),
                        new ResponseBuilder().code("401")
                                .description("Unauthorized!!!").build(),
                        new ResponseBuilder().code("403")
                                .description("Forbidden!!!!!").build(),
                        new ResponseBuilder().code("404")
                                .description("Not Found!!!").build(),
                        new ResponseBuilder().code("500")
                                .description("Internal Server Error!!!").build()
                ))
                .globalResponses(HttpMethod.POST, List.of(
                        new ResponseBuilder().code("200")
                                .description("Very Good!!!").build(),
                        new ResponseBuilder().code("201")
                                .description("Created!!!").build(),
                        new ResponseBuilder().code("400")
                                .description("Bad Request!!!").build(),
                        new ResponseBuilder().code("500")
                                .description("Internal Server Error!!!").build()
                ))
                .globalResponses(HttpMethod.PATCH, List.of(
                        new ResponseBuilder().code("200")
                                .description("Very Good!!!").build(),
                        new ResponseBuilder().code("400")
                                .description("Bad Request!!!").build(),
                        new ResponseBuilder().code("404")
                                .description("Not Found!!!").build(),
                        new ResponseBuilder().code("500")
                                .description("Internal Server Error!!!").build()
                ))
                .globalResponses(HttpMethod.DELETE, List.of(
                        new ResponseBuilder().code("200")
                                .description("Very Good!!!").build(),
                        new ResponseBuilder().code("204")
                                .description("No Content!!!").build(),
                        new ResponseBuilder().code("301")
                                .description("Forward!!!!").build(),
                        new ResponseBuilder().code("302")
                                .description("Redirect!!!").build(),
                        new ResponseBuilder().code("400")
                                .description("Bad Request!!!").build(),
                        new ResponseBuilder().code("500")
                                .description("Internal Server Error!!!").build()
                ))
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "MOTOR DEPOT API",
                "Motor depot is a web application that allows to manage car park.",
                new VersionController().version(),
                "Terms of service",
                new Contact("Vladimir Petranouski", "www.example.com", "vpetranovskij@gmail.com"),
                "Apache 2.0", "https://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
    }
//@Bean
//public OpenAPI springSetlistOrganizerOpenAPI() {
//    return new OpenAPI()
//            .info(new Info().title("Setlist Organizer API")
//                    .description("'Setlist Organizer' is a web application for organizing repertoires of musical bands.")
//                    .version(new VersionController().version())
//                    .license(new License().name("Apache 2.0").url("http://www.apache.org/licenses/LICENSE-2.0)")))
//            .externalDocs(new ExternalDocumentation()
//                    .description("Setlist Organizer github")
//                    .url("https://github.com/Brest-Java-Course-2021-2/Maksim-Meliashchuk-Setlist-Organizer"));
//}
}
