package com.example.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket springBootApis() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .securityContexts(Collections.singletonList(securityContext()))
                .securitySchemes(Collections.singletonList(apiToken()))
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.oddblogger.springbootswagger"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiKey apiToken() {
        return new ApiKey("apiToken", "x-api-token", "header");
    }


    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Collections.singletonList(new SecurityReference("merchantToken", authorizationScopes));
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Your Backend Service APIs",
                "Description of your project.",
                "1.0",
                "",
                new Contact("Support", "https://oddblogger.com", "contact@oddblogger.com"),
                "",
                "",
                Collections.emptyList());
    }
}