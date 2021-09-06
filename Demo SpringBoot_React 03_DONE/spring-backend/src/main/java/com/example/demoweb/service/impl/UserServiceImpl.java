package com.example.demoweb.service.impl;

import com.example.demoweb.constant.RequiredMessage;
import com.example.demoweb.exception.ResourceException;
import com.example.demoweb.model.Employee;
import com.example.demoweb.model.User;
import com.example.demoweb.repository.EmployeeReposity;
import com.example.demoweb.repository.UserRepository;
import com.example.demoweb.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements IService<User> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    public Boolean isFindUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
//        System.out.println("user count "+ user);
        if (user == null) {
            return false;
        }
        return true;
    }

    public Boolean isFindUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        System.out.println("user count "+ user);
        if (user == null) {
            return false;
        }
        return true;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User createOne(User user) {
        Boolean isFindUsernameOfUser = this.isFindUserByUsername(user.getUsername());
        Boolean isFindEmailOfUser = this.isFindUserByEmail(user.getEmail());
        if (!isFindUsernameOfUser && !isFindEmailOfUser) {
            User newUser = new User();
            newUser.setUsername(user.getUsername());
            newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
            newUser.setEmail(user.getEmail());
            newUser.setPhone(user.getPhone());
            return userRepository.save(newUser);
        }
        if (isFindUsernameOfUser) {
            throw new ResourceException(RequiredMessage.USERNAME_EXIST);
        }
        if (isFindEmailOfUser) {
            throw new ResourceException(RequiredMessage.EMAIL_EXIST);
        }
//        throw new ResourceException(RequiredMessage.USERNAME_EXIST);
        return user;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceException(RequiredMessage.USER_NOT_EXISTS + " : " + id));
    }

    @Override
    public User updateOne(Long id, User userDetails) {
        return userRepository.saveAndFlush(userDetails);
    }

    @Override
    public void deleteById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceException(RequiredMessage.USER_NOT_EXISTS + " : " + id));
        userRepository.delete(user);
    }

}
