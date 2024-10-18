package com.fecd.auth.controllers;


import com.fecd.auth.commons.constants.ApiPathConstants;
import com.fecd.auth.commons.dto.CreateUserDto;
import com.fecd.auth.commons.dto.TokenResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.AUTH_ROUTE)
public interface AuthApi {

    @PostMapping("/register")
    ResponseEntity<TokenResponseDto> createUser(@RequestBody @Valid CreateUserDto createUserDto);
}
