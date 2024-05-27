package com.deciploy.backend.modules.api.user;

import com.deciploy.backend.modules.api.company.entity.Company;
import com.deciploy.backend.modules.api.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);

    List<User> findUsersByCompany(Company company);
}
