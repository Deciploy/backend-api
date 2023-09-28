package com.deciploy.backend.modules.api.company;

import com.deciploy.backend.modules.api.auth.AuthService;
import com.deciploy.backend.modules.api.company.dto.CompanyRegisterRequest;
import com.deciploy.backend.modules.api.company.entity.Company;
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
    private CompanyRepository companyRepository;

    public void register(CompanyRegisterRequest companyRegisterRequest) {
        Company company = new Company();
        company.setName(companyRegisterRequest.name());
        company.setEmail(companyRegisterRequest.email());
        company.setAddress(companyRegisterRequest.address());
        company.setContactNumber(companyRegisterRequest.contactNumber());
        company.setContactPerson(companyRegisterRequest.contactPerson());
        company.setCreatedBy(authService.getAuthenticatedUser());

        try {
            companyRepository.save(company);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }
}
