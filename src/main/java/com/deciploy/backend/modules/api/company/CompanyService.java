package com.deciploy.backend.modules.api.company;

import com.deciploy.backend.modules.api.auth.AuthService;
import com.deciploy.backend.modules.api.company.dto.CompanyRegisterRequest;
import com.deciploy.backend.modules.api.company.entity.Company;
import com.deciploy.backend.modules.api.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyRepository companyRepository;

    public void register(CompanyRegisterRequest companyRegisterRequest) {
        if (userService.getUserByEmail(companyRegisterRequest.email()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with this email already exists");
        }
        // TODO: Implement company  validation

        Company company = Company.builder()
                .name(companyRegisterRequest.name())
                .email(companyRegisterRequest.email())
                .address(companyRegisterRequest.address())
                .phone(companyRegisterRequest.phone())
                .createdBy(authService.getAuthenticatedUser())
                .build();
        try {
            company = companyRepository.save(company);

            userService.saveUser(
                    companyRegisterRequest.user().fullName(),
                    companyRegisterRequest.user().email(),
                    companyRegisterRequest.user().password(),
                    new String[]{"MANAGER"},
                    company
            );
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to register company");
        }
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }
}
