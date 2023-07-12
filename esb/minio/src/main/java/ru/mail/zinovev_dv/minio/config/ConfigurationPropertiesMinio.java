package ru.mail.zinovev_dv.minio.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "minio")
@Getter
@Setter
public class ConfigurationPropertiesMinio {
    private String host;

    private Integer port;

    private String login;

    private String password;
}
