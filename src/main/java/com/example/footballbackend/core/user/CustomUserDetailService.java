package com.example.footballbackend.core.user;

import com.example.footballbackend.core.user.dto.User;
import com.example.footballbackend.error.NotFoundException;
import com.example.footballbackend.util.MessageUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserService service;
    private final MessageUtil messageUtil;

    public CustomUserDetailService(UserService service,
                                   MessageUtil messageUtil){
        this.service = service;
        this.messageUtil = messageUtil;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = service.getUserByEmail(email)
                .orElseThrow(() ->
                        new NotFoundException(messageUtil.getMessage("user.email.not-found", email)));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.emptyList());
    }
}
