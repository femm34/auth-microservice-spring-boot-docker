package com.fecd.auth.commons.dto;


import com.fecd.auth.commons.constants.RoleName;
import com.fecd.auth.models.entities.Roles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @NotBlank(message = "Username cannot be empty")
    private String username;

    @NotBlank(message = "Name cannot be empty")
    private String name;

        private Set<Roles> rolesSet;
//    private Set<RoleName> rolesSet = new HashSet<>();

}