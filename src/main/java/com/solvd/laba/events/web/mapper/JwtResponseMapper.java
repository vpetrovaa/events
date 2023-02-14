package com.solvd.laba.events.web.mapper;

import com.solvd.laba.events.domain.jwt.JwtResponse;
import com.solvd.laba.events.web.dto.jwt.JwtResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JwtResponseMapper {

    JwtResponse toEntity(JwtResponseDto jwtResponseDto);

    JwtResponseDto toDto(JwtResponse jwtResponse);

}
