package com.fecd.auth.commons.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {
    private Integer status;
    private String message;
}
