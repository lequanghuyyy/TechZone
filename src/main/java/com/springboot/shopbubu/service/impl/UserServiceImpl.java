package com.springboot.shopbubu.service.impl;

import com.springboot.shopbubu.dto.JwtDto;
import com.springboot.shopbubu.dto.UserDto;
import com.springboot.shopbubu.repository.UserRepository;
import com.springboot.shopbubu.service.UserService;
import com.springboot.shopbubu.utils.ResponseUser;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseUser register(UserDto userDto) {
        return null;
    }

    @Override
    public JwtDto login(UserDto userDto) {
        return null;
    }
}
