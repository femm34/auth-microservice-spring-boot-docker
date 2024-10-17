package com.fecd.auth.services;

import com.fecd.auth.commons.dto.TokenResponseDto;
import io.jsonwebtoken.Claims;

public interface JwtService {
    TokenResponseDto generateToken(Long userId);

    Claims getClaims(String token);

    boolean isExpired(String token);

    Long getUserIdFromToken(String token);
}
