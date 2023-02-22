package com.solvd.laba.events.domain.jwt;

import lombok.Data;

@Data
public class JwtAuth {

    private String email;
    private String password;

}
