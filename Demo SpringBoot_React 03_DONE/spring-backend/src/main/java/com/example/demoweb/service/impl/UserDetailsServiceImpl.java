package com.example.demoweb.service.impl;

import com.example.demoweb.config.CustomUserDetails;
import com.example.demoweb.model.User;
import com.example.demoweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUserDetails customUserDetails = null;

        User user = userRepository.findByUsername(username);
//        System.out.println("USER: "+ user);
//        System.out.println("USER: "+ user.getEmail());
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        customUserDetails = new CustomUserDetails(user.getUsername(), user.getPassword(),
                new ArrayList<>(), user.getEmail(), user.getPhone());
        return customUserDetails;
    }
}
