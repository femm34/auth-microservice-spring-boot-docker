package com.fecd.auth.services.impl;

import com.fecd.auth.commons.dto.TokenResponseDto;
import com.fecd.auth.services.JwtService;
import com.fecd.auth.utils.Base64Encoder;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {
    private final String secretKey;

    public JwtServiceImpl(@Value("${jwt.secret}") String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public TokenResponseDto generateToken(Long userId) {
        String jwtToken = Jwts.builder()
                .setIssuer("BinSolutions")
                .setSubject(String.valueOf(userId))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS512, Base64Encoder.decode(this.secretKey))
                .compact();

        return TokenResponseDto.builder().accessToken(jwtToken).build();
    }

    @Override
    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Base64Encoder.decode(this.secretKey))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public boolean isExpired(String token) {
        try {
            return this.getClaims(token).getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Long getUserIdFromToken(String token) {
        try {
            return Long.parseLong(this.getClaims(token).getSubject());
        } catch (Exception e) {
            return null;
        }
    }
}
