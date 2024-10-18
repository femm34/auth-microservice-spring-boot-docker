package com.fecd.auth.services.impl;

import com.fecd.auth.models.dao.UserDao;
import com.fecd.auth.models.entities.User;
import com.fecd.auth.utils.MapAuthorities;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserDao userDao;

    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userFound = userDao.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Collection<GrantedAuthority> collectionAuthorities = MapAuthorities.execute(userFound.getRoles());

        System.out.println(collectionAuthorities.toString());

        return new org.springframework.security.core.userdetails.User(
                userFound.getUsername(), userFound.getPassword(), MapAuthorities.execute(userFound.getRoles()));
    }
}
