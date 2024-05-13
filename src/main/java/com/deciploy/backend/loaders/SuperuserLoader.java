package com.deciploy.backend.loaders;

import com.deciploy.backend.modules.api.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SuperuserLoader implements ApplicationRunner {
    @Value("${security.super-user.email}")
    String superUserEmail;
    @Value("${security.super-user.password}")
    String superUserPassword;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        loadUsers();
    }

    private void loadUsers() {
        if (userService.getUserByEmail(superUserEmail).isPresent()) {
            return;
        }

        userService.saveUser(
                "Super Admin",
                superUserEmail,
                passwordEncoder.encode(superUserPassword),
                new String[]{"ADMIN"}
        );
    }
}
