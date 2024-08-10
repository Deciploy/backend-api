package com.deciploy.backend.modules.api.application;


import com.deciploy.backend.modules.api.application.entity.Application;
import com.deciploy.backend.modules.api.application.entity.ApplicationType;
import com.deciploy.backend.modules.api.application.repository.ApplicationRepository;
import com.deciploy.backend.modules.api.application.repository.ApplicationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ApplicationTypeRepository applicationTypeRepository;

    public List<Application> getApplications(
            Optional<String> type
    ) {
        if (type.isPresent()) {
            return applicationRepository.findByType(type.get());
        }

        return applicationRepository.findAll();
    }

    public Optional<Application> getApplication(String identifier) {
        return applicationRepository.findByIdentifier(identifier);
    }

    public List<ApplicationType> getApplicationTypes() {
        return applicationTypeRepository.findAll();
    }

    public Optional<ApplicationType> getApplicationTypeFindById(String id) {
        return applicationTypeRepository.findById(id);
    }
}
