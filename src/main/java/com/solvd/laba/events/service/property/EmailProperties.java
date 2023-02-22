package com.solvd.laba.events.service.property;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@RequiredArgsConstructor
@Getter
@ConfigurationProperties(prefix = "spring.mail")
public class EmailProperties {

    private final String reset;
    private final String activate;
    private final String host;
    private final int port;
    private final String username;
    private final String password;

}
