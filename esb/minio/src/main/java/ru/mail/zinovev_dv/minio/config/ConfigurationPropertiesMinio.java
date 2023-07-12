package ru.mail.zinovev_dv.minio.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Конфигурация minio.
 */
@ConfigurationProperties(prefix = "minio")
@Getter
@Setter
public class ConfigurationPropertiesMinio {
    /**
     * Адрес minio.
     */
    private String host;

    /**
     * Порт minio.
     */
    private Integer port;

    /**
     * Пользователь подключения к minio.
     */
    private String login;

    /**
     * Пароль пользователя подключения к minio.
     */
    private String password;
}
