package com.solvd.laba.events.service.impl;

import com.solvd.laba.events.domain.User;
import com.solvd.laba.events.domain.exception.PasswordMismatchException;
import com.solvd.laba.events.domain.exception.ResourceAlreadyExistsException;
import com.solvd.laba.events.domain.exception.ResourceDoesNotExistException;
import com.solvd.laba.events.repository.UserRepository;
import com.solvd.laba.events.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public User create(User user) {
        if (userRepository.isExistByEmail(user.getEmail())) {
            throw new ResourceAlreadyExistsException("Such user is already exists");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.create(user);
        return user;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceDoesNotExistException("There are no user with id" + id));
    }

    @Override
    public User updatePassword(String newPassword, String oldPassword, Long id) {
        User user = findById(id);
        if(!bCryptPasswordEncoder.matches(oldPassword, bCryptPasswordEncoder.encode(oldPassword))){
            throw new PasswordMismatchException("Passwords are different");
        }
        user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        userRepository.updatePassword(user);
        return user;
    }

    @Override
    public User resetPassword(String newPassword, Long id) {
        User user = findById(id);
        user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        userRepository.updatePassword(user);
        return user;
    }

}