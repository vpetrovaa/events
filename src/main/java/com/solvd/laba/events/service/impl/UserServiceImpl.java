package com.solvd.laba.events.service.impl;

import com.solvd.laba.events.domain.Password;
import com.solvd.laba.events.domain.User;
import com.solvd.laba.events.domain.exception.PasswordMismatchException;
import com.solvd.laba.events.domain.exception.ResourceAlreadyExistsException;
import com.solvd.laba.events.domain.exception.ResourceDoesNotExistException;
import com.solvd.laba.events.repository.UserRepository;
import com.solvd.laba.events.service.EmailService;
import com.solvd.laba.events.service.JwtService;
import com.solvd.laba.events.service.UserService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtService jwtService;
    private final EmailService emailService;

    @Override
    @Transactional
    public User create(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ResourceAlreadyExistsException("User with email " + user.getEmail() + " already exists");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        Map<String, Object> params = new HashMap<>();
        String token = jwtService.generateActivatingToken(user);
        params.put("token", token);
        emailService.sendActivationEmail(user, params);
        return user;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceDoesNotExistException("There are no user with id" + id));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceDoesNotExistException("There are no user with such email"));
    }

    @Override
    public User updatePassword(Password password, Long id) {
        User user = findById(id);
        if (!bCryptPasswordEncoder.matches(password.getOldPassword(), user.getPassword())) {
            throw new PasswordMismatchException("Passwords are different");
        }
        user.setPassword(bCryptPasswordEncoder.encode(password.getNewPassword()));
        userRepository.save(user);
        return user;
    }

    @Override
    public User resetPassword(String newPassword, Long id) {
        User user = findById(id);
        user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        userRepository.save(user);
        return user;
    }

    @Override
    public User updateStatus(User user) {
        user.setActivated(true);
        userRepository.save(user);
        return user;
    }

    @Override
    public User activate(String token) {
        String email = jwtService.extractClaim(token, Claims::getSubject);
        User user = findByEmail(email);
        return updateStatus(user);
    }

}
