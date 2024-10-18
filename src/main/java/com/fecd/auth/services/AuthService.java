package com.fecd.auth.services;

import com.fecd.auth.commons.dto.CreateUserDto;
import com.fecd.auth.commons.dto.TokenResponseDto;

public interface AuthService {
    TokenResponseDto login(String username, String password);

    TokenResponseDto register(CreateUserDto createUserDto);
}
