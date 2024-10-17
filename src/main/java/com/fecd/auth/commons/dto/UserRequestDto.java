package com.fecd.auth.commons.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    @NotNull
    private String password;
    @NotNull
    private String email;
    @NotNull
    private String username;
}
