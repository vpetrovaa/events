package com.solvd.laba.events.web;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class DateFormatConfig {

    private static final String dateTimeFormat = "yyyy/MM/dd HH:mm";

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsomCustomizer() {
        return builder -> builder.simpleDateFormat(dateTimeFormat)
                .serializerByType(LocalDateTime.class,
                        new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)))
                .deserializerByType(LocalDateTime.class,
                        new LocalDateDeserializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
    }

}
