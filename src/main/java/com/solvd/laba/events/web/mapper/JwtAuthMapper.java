package com.solvd.laba.events.web.mapper;

import com.solvd.laba.events.domain.jwt.JwtAuth;
import com.solvd.laba.events.web.dto.jwt.JwtAuthDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JwtAuthMapper {

    JwtAuth toEntity(JwtAuthDto dto);

    JwtAuthDto toDto(JwtAuth entity);

}
