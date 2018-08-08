package com.romantupikov.gbspring.security.config;

import com.romantupikov.gbspring.security.entity.Role;
import com.romantupikov.gbspring.security.entity.User;
import com.romantupikov.gbspring.security.service.RoleService;
import com.romantupikov.gbspring.security.service.UserService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    private final UserService userService;
    private final RoleService roleService;


    public InitialDataLoader(UserService userService,
                             RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Transactional
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;

        Role userRole = roleService.createRoleIfNotFound("ROLE_USER");
        Role adminRole = roleService.createRoleIfNotFound("ROLE_ADMIN");

        User user = new User();
        user.setUsername("admin");
        user.setPassword("password");
        user.getRoles().add(userRole);
        user.getRoles().add(adminRole);
        user.setEnabled(true);
        userService.register(user);

        alreadySetup = true;
    }

}