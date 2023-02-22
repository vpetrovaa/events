package com.solvd.laba.events.web.mapper;

import com.solvd.laba.events.domain.jwt.JwtReset;
import com.solvd.laba.events.web.dto.jwt.JwtResetDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JwtResetMapper {

    JwtReset toEntity(JwtResetDto dto);

    JwtResetDto toDto(JwtReset entity);

}
