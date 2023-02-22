package com.solvd.laba.events.web.mapper;

import com.solvd.laba.events.domain.jwt.JwtRefresh;
import com.solvd.laba.events.web.dto.jwt.JwtRefreshDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JwtRefreshMapper {

    JwtRefresh toEntity(JwtRefreshDto dto);

    JwtRefreshDto toDto(JwtRefresh entity);

}
