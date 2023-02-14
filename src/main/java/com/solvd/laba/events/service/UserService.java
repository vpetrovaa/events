package com.solvd.laba.events.service;

import com.solvd.laba.events.domain.Password;
import com.solvd.laba.events.domain.User;

public interface UserService {

    User create(User user);

    User findById(Long id);

    User findByEmail(String email);

    User updatePassword(Password password, Long id);

    User resetPassword(String newPassword, Long id);

}
