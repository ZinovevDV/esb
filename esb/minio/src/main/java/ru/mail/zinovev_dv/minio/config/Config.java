package ru.mail.zinovev_dv.minio.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mail.zinovev_dv.minio.service.Minio;

@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties(ConfigurationPropertiesMinio.class)
@RequiredArgsConstructor
public class Config {
    private final ConfigurationPropertiesMinio propertiesMinio;
    @Bean
    Minio minio(){
        return new Minio(propertiesMinio.getHost(), propertiesMinio.getPort(), propertiesMinio.getLogin(), propertiesMinio.getPassword()) ;
    }
}
