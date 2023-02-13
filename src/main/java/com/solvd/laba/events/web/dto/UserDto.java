package com.solvd.laba.events.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solvd.laba.events.domain.User;
import com.solvd.laba.events.web.dto.validation.OnCreate;
import com.solvd.laba.events.web.dto.validation.OnUpdate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserDto {

    @NotNull(message = "Id is required", groups = {OnUpdate.class})
    private Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private User.Role role;

    @NotNull(message = "Password is required", groups = {OnCreate.class})
    @Length(min = 8, message = "Password must be longer than 8 characters", groups = {OnCreate.class})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotNull(message = "Email is required", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 45, message = "Email must be shorter than 45 characters", groups = {OnCreate.class, OnUpdate.class})
    @Email(message = "Email is not valid", groups = {OnCreate.class, OnUpdate.class})
    private String email;

    @NotNull(message = "Name is required", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 45, message = "Name must be shorter than 45 characters", groups = {OnCreate.class, OnUpdate.class})
    private String name;

    @NotNull(message = "Surname is required", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 45, message = "Surname must be shorter than 45 characters", groups = {OnCreate.class, OnUpdate.class})
    private String surname;

}
