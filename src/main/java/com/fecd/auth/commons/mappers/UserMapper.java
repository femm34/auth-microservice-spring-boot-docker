package com.fecd.auth.commons.mappers;

import com.fecd.auth.commons.dto.CreateUserDto;
import com.fecd.auth.models.entities.User;
import com.fecd.auth.utils.MyPasswordEncoder;

public class UserMapper {
    public static User toUser(CreateUserDto createUserDto, MyPasswordEncoder myPasswordEncoder) {
        return User.builder()
                .email(createUserDto.getEmail())
                .name(createUserDto.getName())
                .password(myPasswordEncoder.encode(createUserDto.getPassword()))
                .username(createUserDto.getUsername())
                .roles(createUserDto.getRolesSet())
                .build();
    }
}
