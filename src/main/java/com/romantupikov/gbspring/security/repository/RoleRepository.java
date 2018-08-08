package com.romantupikov.gbspring.security.repository;

import com.romantupikov.gbspring.security.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {

    Optional<Role> findByRoleName(String name);
}
