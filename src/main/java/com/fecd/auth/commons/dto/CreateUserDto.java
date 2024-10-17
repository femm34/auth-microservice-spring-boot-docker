package com.fecd.auth.commons.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {
    @NotNull
    private String password;
    @NotNull
    private String name;
    @Email
    private String email;
    @NotNull
    private String username;
}
