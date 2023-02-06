package com.solvd.laba.events.service;

import com.solvd.laba.events.domain.User;

public interface UserService {

    User create(User user);

    User findById(Long id);

    User updatePassword(String newPassword, String oldPassword, Long id);

    User resetPassword(String newPassword, Long id);

}
