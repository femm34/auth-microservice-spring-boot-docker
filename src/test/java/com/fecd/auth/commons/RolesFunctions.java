package com.fecd.auth.commons;

import com.fecd.auth.commons.constants.RoleName;
import com.fecd.auth.models.entities.Roles;

import java.util.HashSet;
import java.util.Set;

public abstract class RolesFunctions {
    public static Set<Roles> createRolesSet(Set<RoleName> roles) {
        Set<Roles> rolesSet = new HashSet<>();

        for (RoleName role : roles) {
            rolesSet.add(Roles.builder().name(role).build());
        }

        return rolesSet;
    }
}
