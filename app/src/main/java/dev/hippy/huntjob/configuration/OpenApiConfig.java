package dev.hippy.huntjob.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
    info = @Info(
        title = "HuntJob Api",
        description = "Application that helps keep a record of all the companies whose jobs you have responded", version = "1.0.0",
        contact = @Contact(
            name = "hippy",
            email = "realhippy0@gmail.com"
        )
    )
)
public class OpenApiConfig {

}