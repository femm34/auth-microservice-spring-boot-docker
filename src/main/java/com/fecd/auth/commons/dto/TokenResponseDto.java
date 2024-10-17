package com.fecd.auth.commons.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponseDto {
    private String accessToken;
}
