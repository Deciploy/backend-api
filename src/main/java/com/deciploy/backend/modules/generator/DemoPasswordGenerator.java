package com.deciploy.backend.modules.generator;

import org.springframework.stereotype.Component;

@Component
public class DemoPasswordGenerator implements PasswordGenerator {
    @Override
    public String generatePassword() {
        return "dev@1234";
    }
}
