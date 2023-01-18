package ru.prostopodpis.api.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 11.09.2022
 **/

@Configuration
@ConfigurationProperties(prefix = "cors")
@EnableWebFlux
@Data
public class CORSConfiguration implements WebFluxConfigurer {

    private Integer maxAge;

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry
                .addMapping("/api/v1/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .exposedHeaders("Authorization", "Access-Control-Allow-Origin")
                .maxAge(getMaxAge());
    }
}
