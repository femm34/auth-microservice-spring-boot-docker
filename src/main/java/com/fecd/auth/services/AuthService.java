package com.fecd.auth.services;

import com.fecd.auth.commons.dto.CreateUserDto;
import com.fecd.auth.commons.dto.LoginDto;
import com.fecd.auth.commons.dto.TokenResponseDto;

public interface AuthService {
    TokenResponseDto login(LoginDto loginDto);

    TokenResponseDto register(CreateUserDto createUserDto);
}
