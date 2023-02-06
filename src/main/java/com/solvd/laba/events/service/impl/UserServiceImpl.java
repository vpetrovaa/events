package com.solvd.laba.events.service.impl;

import com.solvd.laba.events.domain.User;
import com.solvd.laba.events.domain.exception.ResourceAlreadyExistsException;
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

}
