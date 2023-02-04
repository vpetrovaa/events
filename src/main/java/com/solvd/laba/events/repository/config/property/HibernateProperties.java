package com.solvd.laba.events.repository.config.property;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@RequiredArgsConstructor
@Getter
@ConfigurationProperties(prefix = "spring.datasource")
public class HibernateProperties {

    private final String url;
    private final String username;
    private final String password;
    private final String driver;

}
