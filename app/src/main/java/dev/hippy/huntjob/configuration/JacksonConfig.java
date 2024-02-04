package dev.hippy.huntjob.configuration;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.boot.jackson.JsonComponentModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {

    @Bean
    Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();

        builder.serializationInclusion(Include.NON_NULL);
        builder.modulesToInstall(new JsonComponentModule());

        return builder;
    }
}
