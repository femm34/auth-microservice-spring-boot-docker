package com.fecd.auth.controllers.impl;

import com.fecd.auth.commons.dto.CreateUserDto;
import com.fecd.auth.commons.dto.TokenResponseDto;
import com.fecd.auth.controllers.AuthApi;
import com.fecd.auth.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthControllerImpl implements AuthApi {

    private final AuthService authService;

    public AuthControllerImpl(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public ResponseEntity<TokenResponseDto> createUser(CreateUserDto createUserDto) {
        System.out.println(createUserDto.toString());
        return ResponseEntity.ok(authService.register(createUserDto));
    }
}
