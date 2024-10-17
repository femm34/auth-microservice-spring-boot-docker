package com.fecd.auth.services.impl;

import com.fecd.auth.models.dao.UserDao;
import com.fecd.auth.models.entities.Roles;
import com.fecd.auth.models.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserDao userDao;

    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public Collection<GrantedAuthority> mapToAuhtorities(Set<Roles> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().toString()))
                .collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userFound = userDao.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Collection<GrantedAuthority> collectionAuthorities = mapToAuhtorities(userFound.getRoles());

        System.out.println(collectionAuthorities.toString());

        return new org.springframework.security.core.userdetails.User(
                userFound.getUsername(), userFound.getPassword(), mapToAuhtorities(userFound.getRoles()));
    }
}
