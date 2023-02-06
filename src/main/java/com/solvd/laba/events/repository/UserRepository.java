package com.solvd.laba.events.repository;

import com.solvd.laba.events.domain.User;

import java.util.Optional;

public interface UserRepository {

    void create(User user);

    boolean isExistByEmail(String email);

    void updatePassword(User user);

    Optional<User> findById(Long id);

}
