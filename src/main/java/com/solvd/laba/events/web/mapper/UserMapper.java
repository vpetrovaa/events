package com.solvd.laba.events.web.mapper;

import com.solvd.laba.events.domain.User;
import com.solvd.laba.events.web.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {TicketMapper.class})
public interface UserMapper {

    UserDto entityToDto(User user);

    User dtoToEntity(UserDto userDto);

}
