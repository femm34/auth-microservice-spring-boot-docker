package com.fecd.auth.utils;

import com.fecd.auth.models.entities.Roles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class MapAuthorities {
    public static Collection<GrantedAuthority> execute(Set<Roles> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().toString()))
                .collect(Collectors.toList());
    }
}
