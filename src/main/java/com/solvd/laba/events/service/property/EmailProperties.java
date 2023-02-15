package com.solvd.laba.events.service.property;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@RequiredArgsConstructor
@Getter
@ConfigurationProperties(prefix = "email.properties")
public class EmailProperties {

    private final String reset;
    private final String activate;

}
