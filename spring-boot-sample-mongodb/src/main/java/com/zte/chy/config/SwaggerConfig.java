package com.zte.chy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).pathMapping("/").select()
				.apis(RequestHandlerSelectors.basePackage("com.zte.chy.control")).paths(PathSelectors.any()).build();
	}

	@Bean
	public ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("关于 MongoDB RESTFUL APIs").termsOfServiceUrl("http://10.5.76.89:31007/")
				.contact(new Contact("mw10205267/chy10191029", "http://10.5.76.89:31007/", "ma.wen@zte.com.cn"))
				.description("生产管控 MongoDB数据操作 APIs").version("1.1.0").build();
	}

}
