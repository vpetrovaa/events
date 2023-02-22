package com.solvd.laba.events.service.property;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@RequiredArgsConstructor
@Getter
@ConfigurationProperties(prefix = "jwt.properties")
public class JwtProperties {

    private final Long access;
    private final Long refresh;
    private final Long reset;
    private final Long activating;
    private final String secret;

}
