package com.rdecastropavon.pricemicroservice.application.rest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "${api.title}", version = "${api.version}", description = "${api.description}"))
public class OpenAPIConfiguration {

}