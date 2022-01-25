package com.citiustech.orderms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

// http://localhost:8081/swagger-ui/
// http://localhost:8081/swagger-ui/index.html

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()) // PathSelectors.ant("/v2/**")
				.build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Title: Standalone Order API").version("1.0")
				.description("API for managing Orders")
				.contact(new Contact("abc", "http://abc.com", "abc@swagger.com")).license("IMPACT License Version 1.1")
				.build();
	}

}