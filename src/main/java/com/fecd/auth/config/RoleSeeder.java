package com.fecd.auth.config;


import com.fecd.auth.commons.constants.RoleName;
import com.fecd.auth.models.dao.RoleDao;
import com.fecd.auth.models.entities.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RoleSeeder implements CommandLineRunner {

    @Autowired
    private RoleDao roleDao;

    @Override
    public void run(String... args) throws Exception {
        seedRole(RoleName.ROLE_ADMIN);
        seedRole(RoleName.ROLE_USER);
        seedRole(RoleName.ROLE_MODERATOR);
    }

    private void seedRole(RoleName roleName) {
        Optional<Roles> existingRole = roleDao.findByName(roleName);

        if (existingRole.isEmpty()) {
            Roles newRole = Roles.builder().name(roleName).build();
            roleDao.save(newRole);
            System.out.println("Role " + roleName + " has been added to the database.");
        } else {
            System.out.println("Role " + roleName + " already exists in the database.");
        }
    }
}