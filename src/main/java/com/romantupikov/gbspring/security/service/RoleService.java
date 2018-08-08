package com.romantupikov.gbspring.security.service;

import com.romantupikov.gbspring.security.entity.Role;
import com.romantupikov.gbspring.security.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role createRoleIfNotFound(String roleName) {

        Optional<Role> optionalRole = roleRepository.findByRoleName(roleName);

        if (optionalRole.isPresent())
            return optionalRole.get();

        Role role = new Role();
        role.setRoleName(roleName);
        roleRepository.save(role);
        return role;
    }

    Role getBasicRole() {
        return createRoleIfNotFound("ROLE_USER");
    }
}
