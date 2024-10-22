package com.fecd.auth.models.entities;

import com.fecd.auth.commons.RolesFunctions;
import com.fecd.auth.commons.constants.RoleName;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    @Test
    void testUserCreation() {
        Set<RoleName> roleNames = EnumSet.of(RoleName.ROLE_ADMIN, RoleName.ROLE_USER, RoleName.ROLE_MODERATOR);
        Set<Roles> adminRoles = RolesFunctions.createRolesSet(roleNames);

        User user = User.builder()
                .name("Fernando Emanuel")
                .username("fecd")
                .password("123456")
                .email("fmartinez@casystems.com.mx")
                .roles(adminRoles)
                .id(1L)
                .build();

        assertEquals("Fernando Emanuel", user.getName());
        assertEquals("123456", user.getPassword());
        assertEquals("fmartinez@casystems.com.mx", user.getEmail());
        assertEquals(adminRoles, user.getRoles());
        assertEquals(1, user.getId());
        assertEquals("fecd", user.getUsername());
    }
}
