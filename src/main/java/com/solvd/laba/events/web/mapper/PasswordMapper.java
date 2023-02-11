package com.solvd.laba.events.web.mapper;

import com.solvd.laba.events.domain.Password;
import com.solvd.laba.events.web.dto.PasswordDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PasswordMapper {

    Password toEntity(PasswordDto dto);

    PasswordDto toDto(Password entity);

}
