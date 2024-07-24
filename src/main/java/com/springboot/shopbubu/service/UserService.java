package com.springboot.shopbubu.service;

import com.springboot.shopbubu.dto.JwtDto;
import com.springboot.shopbubu.dto.UserDto;
import com.springboot.shopbubu.dto.common.ResponseUser;

public interface UserService {
 ResponseUser register(UserDto userDto);
JwtDto login(UserDto userDto);
}
