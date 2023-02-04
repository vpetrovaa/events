package com.solvd.laba.events.repository;

import com.solvd.laba.events.domain.User;

public interface UserRepository {

    void create(User user);

    boolean isExistByEmail(String email);

}
