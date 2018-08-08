package com.romantupikov.gbspring.security.repository;

import com.romantupikov.gbspring.aop.annotation.LogBefore;
import com.romantupikov.gbspring.security.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    @LogBefore
    Optional<Role> findByRoleName(String name);
}
