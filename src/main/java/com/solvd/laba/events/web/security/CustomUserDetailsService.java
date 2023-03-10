package com.solvd.laba.events.web.security;

import com.solvd.laba.events.domain.User;
import com.solvd.laba.events.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByEmail(username);
        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .authorities(user.getRole().getAuthority())
                .build();
    }

}
