package com.fecd.auth.models.dao;

import com.fecd.auth.commons.constants.RoleName;
import com.fecd.auth.models.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleDao extends JpaRepository<Roles, Long> {
    Optional<Roles> findByName(RoleName name);
}
