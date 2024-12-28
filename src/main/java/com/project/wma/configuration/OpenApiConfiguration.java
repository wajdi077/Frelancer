package com.project.wma.configuration;


import org.springdoc.core.configuration.SpringDocConfiguration;
import org.springdoc.core.configuration.SpringDocUIConfiguration;
import org.springdoc.core.properties.SpringDocConfigProperties;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springdoc.core.providers.ObjectMapperProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class OpenApiConfiguration {
    static class SpringDocConfigFix extends SpringDocUIConfiguration {

        public SpringDocConfigFix(Optional<SwaggerUiConfigProperties> optionalSwaggerUiConfigProperties) {
            super(optionalSwaggerUiConfigProperties);
        }
    }

    @Bean
    @ConditionalOnProperty(value = "springdoc.api-docs.enabled", havingValue = "false")
    SpringDocConfigFix springDocConfigFix(SwaggerUiConfigProperties props) {
        return new SpringDocConfigFix(Optional.of(props));
    }

    @Bean
    SpringDocConfigProperties springDocConfigProperties() {
        return new SpringDocConfigProperties();
    }

    @Bean
    ObjectMapperProvider objectMapperProvider() {
        return new ObjectMapperProvider(springDocConfigProperties());
    }
}