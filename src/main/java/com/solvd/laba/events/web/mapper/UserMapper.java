package com.solvd.laba.events.web.mapper;

import com.solvd.laba.events.domain.User;
import com.solvd.laba.events.web.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(UserDto userDto);

}
