package com.solvd.laba.events.web.dto;

import com.solvd.laba.events.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private Long id;
    private User.Role role;
    private String password;
    private String email;
    private String name;
    private String surname;

}
