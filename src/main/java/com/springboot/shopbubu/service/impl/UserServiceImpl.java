package com.springboot.shopbubu.service.impl;

import com.springboot.shopbubu.dto.JwtDto;
import com.springboot.shopbubu.dto.UserDto;
import com.springboot.shopbubu.entity.UserEntity;
import com.springboot.shopbubu.exception.AlreadyExistsException;
import com.springboot.shopbubu.repository.RoleRepository;
import com.springboot.shopbubu.repository.UserRepository;
import com.springboot.shopbubu.service.UserService;
import com.springboot.shopbubu.dto.response.ResponseUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public ResponseUser register(UserDto user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new AlreadyExistsException("User", "username", user.getUsername());
        }
        if (user.getUsername() == null || user.getUsername().isBlank()){
            throw new IllegalArgumentException("USERNAME IS NULL");
        }
        for (String role : user.getRoles()) {
            if (!role.equals("ADMIN") && !role.equals("USER")) {
                throw new IllegalArgumentException("Invalid role: " + role);
            }
        }
        UserEntity entity = new UserEntity();
        entity.setUsername(user.getUsername());
        entity.setRoles(roleRepository.findAllByRoleNameIn(user.getRoles()));
        UserEntity savedUser = userRepository.save(entity);

        ResponseUser respUser = new ResponseUser();
        respUser.setUsername(user.getUsername());
        respUser.setId(savedUser.getId());
        respUser.setRoles(user.getRoles());
        return respUser;
    }

    @Override
    public JwtDto login(UserDto userDto) {
        return null;
    }
}
