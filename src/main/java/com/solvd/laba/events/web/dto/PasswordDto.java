package com.solvd.laba.events.web.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class PasswordDto {

    @NotEmpty(message = "old password is required")
    private String oldPassword;

    @NotEmpty(message = "new password is required")
    @Length(min = 8, message = "password must be at least 8 characters long")
    private String newPassword;

}
