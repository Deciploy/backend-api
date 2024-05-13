package com.deciploy.backend.modules.api.user;

import com.deciploy.backend.modules.api.auth.AuthService;
import com.deciploy.backend.modules.api.company.CompanyRepository;
import com.deciploy.backend.modules.api.company.entity.Company;
import com.deciploy.backend.modules.api.team.TeamRepository;
import com.deciploy.backend.modules.api.team.entity.Team;
import com.deciploy.backend.modules.api.user.dto.CreateUserRequest;
import com.deciploy.backend.modules.api.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private AuthService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private TeamRepository teamRepository;

    public void saveUser(String fullName, String email, String password, String[] roles) {
        User user = User.builder()
                .fullName(fullName)
                .email(email)
                .password(passwordEncoder.encode(password))
                .roles(List.of(roles))
                .build();
        userRepository.save(user);
    }

    public void saveUser(CreateUserRequest createUserRequest) {
        if (userRepository.findByEmail(createUserRequest.email()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with this email already exists");
        }

        Company company = null;
        Team team = null;
        User authenticatedUser = authService.getAuthenticatedUser();

        if (Arrays.asList(createUserRequest.roles()).contains("MANAGER")) {
            if (createUserRequest.companyId() != null) {
                company = companyRepository.findById(createUserRequest.companyId()).orElse(null);
            } else if (authenticatedUser.getCompany() != null) {
                company = authenticatedUser.getCompany();
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Company ID is required");
            }
        }

        if (Arrays.asList(createUserRequest.roles()).contains("EMPLOYEE")) {
            if (createUserRequest.teamId() != null) {
                team = teamRepository.findById(createUserRequest.teamId()).orElse(null);
                company = authenticatedUser.getCompany();
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Team ID is required");
            }
        }

        User user = User.builder()
                .fullName(createUserRequest.fullName())
                .email(createUserRequest.email())
                .password(passwordEncoder.encode(createUserRequest.password()))
                .roles(List.of(createUserRequest.roles()))
                .company(company)
                .team(team)
                .build();
        userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public List<User> findAll() {
        return userRepository.findUsersByCompany(authService.getAuthenticatedUser().getCompany());
    }

    public void updateUser(String id, CreateUserRequest createUserRequest) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        user.setFullName(createUserRequest.fullName());
        user.setEmail(createUserRequest.email());
        user.setRoles(createUserRequest.roles());
        userRepository.save(user);
    }

    public void deleteUser(String id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        userRepository.delete(user);
    }
}
