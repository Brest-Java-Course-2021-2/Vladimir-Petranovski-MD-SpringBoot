package com.epam.brest.rest.config;

import com.epam.brest.rest.controller.VersionController;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.DocumentationContextBuilder;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.Map;

@Configuration
public class SpringFoxConfig {

    /**
     * @return Docket class.
     * @bean configure swagger openApi.
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
                new Contact("Vladimir Petranouski",
                        "www.example.com", "vpetranovskij@gmail.com"),
                "Apache 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",
                Collections.emptyList());
    }
//
//    @Bean
//    public OpenAPI openAPI() {
//
//        return new OpenAPI()
//                .externalDocs(new ExternalDocumentation().description("GitHub Repository")
//                        .url("https://github.com/Brest-Java-Course-2021-2/VLadimir-Petranovski-MD-SpringBoot"))
//                .components(new Components().addSchemas("errorMessage",
//                                buildObjectSchema(Map.of("message", new StringSchema()))
//                                        .description("Server error message"))
//                        .addSchemas("validationErrorsMessage",
//                                buildObjectSchema(Map.of("validationErrors", new Schema<Map<String, String>>()))
//                                        .description("Validation errors in the request body"))
//                        .addSchemas("personalDataDto",
//                                buildObjectSchema(Map.of("customer", new StringSchema().example("Sergey Sergeev")))
//                                        .description("Bank customer personal data transfer object"))
//                        .addSchemas("updatedPersonalDataDto",
//                                buildObjectSchema(Map.of("id", new IntegerSchema().example(1),
//                                        "customer", new StringSchema().example("Ivan Ivanoff")))
//                                        .description("Bank customer personal data transfer object for updating"))
//                        .addSchemas("bankAccountId", new IntegerSchema().example(1).description("Bank account ID"))
//                        .addSchemas("depositTransactionDto",
//                                buildObjectSchema(Map.of("targetCardNumber",
//                                        new StringSchema().example("4000003394112581").description("Number of a target credit card"),
//                                        "transactionAmountValue",
//                                        new StringSchema().example("1500,00").description("String representation of a transaction amount"),
//                                        "locale", new StringSchema().example("ru").description("Current locale")))
//                                        .description("Deposit transaction data transfer object"))
//                        .addSchemas("version",
//                                new Schema<Map<String, String>>()
//                                        .addProperties("version", new StringSchema().example("1.0"))
//                                        .description("Current controller version"))
////                        .addSchemas("Driver",
////                                buildObjectSchema(Map.of("driverId",
////                                        new IntegerSchema().getNot())))
////                                        new IntegerSchema().getNot(),
////                                        "driverName", new StringSchema().example("Uladzimir").description("driver's name"),
////                                        "driverDateStartWork", new StringSchema().example("2000-01-01T04:01:01.001Z").description("Driver's start work date"),
////                                        "driverSalary", new StringSchema().example("8000").description("driver's salary")))
//
//
//                        );
//    }
//
//    private Schema buildObjectSchema(Map<String, Schema> properties) {
//        return new ObjectSchema().properties(properties);
//    }
}
