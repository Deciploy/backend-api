package com.deciploy.backend.modules.api.company;

import com.deciploy.backend.modules.api.common.ApiResponse;
import com.deciploy.backend.modules.api.company.dto.CompanyRegisterRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/company")
@Secured("ADMIN")
@Tag(name = "Company", description = "Company API for create and manage company")
@SecurityRequirement(name = "Bearer Authentication")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping()
    public ResponseEntity register(@Valid @RequestBody CompanyRegisterRequest companyRegisterRequest) {
        companyService.register(companyRegisterRequest);
        return ApiResponse.success("Company registered successfully");
    }

    @GetMapping()
    public ResponseEntity findAll() {
        return ApiResponse.data(companyService.findAll());
    }
}
