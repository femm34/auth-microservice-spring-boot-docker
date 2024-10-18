package com.fecd.auth.services.impl;

import com.fecd.auth.commons.dto.CreateUserDto;
import com.fecd.auth.commons.dto.TokenResponseDto;
import com.fecd.auth.commons.exceptions.AuthException;
import com.fecd.auth.commons.mappers.UserMapper;
import com.fecd.auth.models.dao.RoleDao;
import com.fecd.auth.models.dao.UserDao;
import com.fecd.auth.models.entities.Roles;
import com.fecd.auth.models.entities.User;
import com.fecd.auth.services.AuthService;
import com.fecd.auth.services.JwtService;
import com.fecd.auth.utils.MyPasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class AuthServiceImpl implements AuthService {
    private final UserDao userDao;
    private final RoleDao roleDao;
    private final JwtService jwtService;
    private final MyPasswordEncoder myPasswordEncoder;

    public AuthServiceImpl(UserDao userDao, RoleDao roleDao, JwtService jwtService, MyPasswordEncoder myPasswordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.jwtService = jwtService;
        this.myPasswordEncoder = myPasswordEncoder;
    }


    @Override
    public TokenResponseDto login(String username, String password) {
        return null;
    }

    @Override
    public TokenResponseDto register(CreateUserDto createUserDto) {
        System.out.println("--------------------------------------");
        return Optional.of(createUserDto)
                .map(userToCreate -> {
                    var user = UserMapper.toUser(userToCreate, myPasswordEncoder);
                    Set<Roles> roles = userToCreate.getRolesSet().stream()
                            .map(Roles::getName)
                            .map(roleName -> roleDao.findByName(roleName)
                                    .orElseThrow(() -> new AuthException("Role not found: " + roleName, HttpStatus.BAD_REQUEST)))
                            .collect(Collectors.toSet());

                    user.setRoles(roles);
                    return user;
                })
                .map(userDao::save)
                .map(userCreated -> jwtService.generateToken(userCreated.getId()))
                .orElseThrow(() -> new AuthException("Error, trying again later", HttpStatus.BAD_REQUEST));
    }
}
