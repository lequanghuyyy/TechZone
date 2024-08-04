package com.springboot.shopbubu.service.impl;

import com.springboot.shopbubu.dto.JwtDto;
import com.springboot.shopbubu.dto.UserDto;
import com.springboot.shopbubu.dto.response.TokenResponse;
import com.springboot.shopbubu.entity.UserEntity;
import com.springboot.shopbubu.exception.AlreadyExistsException;
import com.springboot.shopbubu.exception.InvalidCredentialsException;
import com.springboot.shopbubu.repository.RoleRepository;
import com.springboot.shopbubu.repository.UserRepository;
import com.springboot.shopbubu.security.CustomUserDetails;
import com.springboot.shopbubu.security.JwtTokenProvider;
import com.springboot.shopbubu.service.UserService;
import com.springboot.shopbubu.dto.response.ResponseUser;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final RoleRepository roleRepository;

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
        entity.setPassword(passwordEncoder.encode(user.getPassword()));
        entity.setUsername(user.getUsername());
        entity.setRoles(roleRepository.findAllByRoleNameIn(user.getRoles()));
        UserEntity savedUser = userRepository.save(entity);

        ResponseUser respUser = new ResponseUser();
        respUser.setUsername(user.getUsername());
        respUser.setId(savedUser.getId());
        respUser.setRoles(user.getRoles());
        return respUser;
    }

    public JwtDto login(UserDto user) {
        try {
            // Perform authentication
            // 1. pass encoder hash password
            // 2. userDetail service
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

            // Retrieve user details from the authenticated token
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            // Generate JWT token
            String accessToken = jwtTokenProvider.generateToken(userDetails);
            Date expriedDate = jwtTokenProvider.extractExpiration(accessToken);

            return JwtDto.builder()
                    .token(accessToken)
                    .expiredIn(expriedDate)
                    .build();
        } catch (AuthenticationException e) {
            // Handle authentication failure
            log.error("Wrong username or password {}", e.getMessage(), e);
            throw new InvalidCredentialsException("Wrong username or password");
        }
    }

//    @Override
//    public TokenResponse refreshToken(HttpServletRequest request) {
//        String refreshToken = request.getHeader("Refresh-Token");
//        if (StringUtils.isBlank(refreshToken)) {
//            throw new InvalidCredentialsException("Refresh Token is empty");
//        }
//        final String userName = jwtTokenProvider.extractUsername(refreshToken);
//        Optional<UserEntity> user = userRepository.findByUsername(userName);
//        if (!jwt)
//    }
}
