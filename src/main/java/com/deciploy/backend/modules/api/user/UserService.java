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

    public User saveUser(String fullName, String email, String password, String[] roles) {
        User user = new User(fullName, email, password, roles, null, null);
        return userRepository.save(user);
    }

    public void saveUser(CreateUserRequest createUserRequest) {
        if (userRepository.findByEmail(createUserRequest.email()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with this email already exists");
        }

        Company company = null;
        Team team = null;
        User authenticatedUser = authService.getAuthenticatedUser();

        if (Arrays.stream(createUserRequest.roles()).anyMatch(role -> role.equals("MANAGER"))) {
            if (createUserRequest.companyId() != null) {
                company = companyRepository.findById(createUserRequest.companyId()).orElse(null);
            } else if (authenticatedUser.getCompany() != null) {
                company = authenticatedUser.getCompany();
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Company ID is required");
            }
        }

        if (Arrays.stream(createUserRequest.roles()).anyMatch(role -> role.equals("EMPLOYEE"))) {
            if (createUserRequest.teamId() != null) {
                team = teamRepository.findById(createUserRequest.teamId()).orElse(null);
                company = authenticatedUser.getCompany();
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Team ID is required");
            }
        }

        User user = new User(
                createUserRequest.fullName(),
                createUserRequest.email(),
                passwordEncoder.encode(createUserRequest.password()),
                createUserRequest.roles(),
                company,
                team
        );
        userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public List<User> findAll() {
        return userRepository.findUsersByCompany(authService.getAuthenticatedUser().getCompany());
    }
}
