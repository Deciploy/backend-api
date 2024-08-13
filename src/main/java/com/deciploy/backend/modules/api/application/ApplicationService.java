package com.deciploy.backend.modules.api.application;


import com.deciploy.backend.modules.api.application.dto.ApplicationUsage;
import com.deciploy.backend.modules.api.application.dto.UsageFilter;
import com.deciploy.backend.modules.api.application.entity.Application;
import com.deciploy.backend.modules.api.application.entity.ApplicationType;
import com.deciploy.backend.modules.api.application.repository.ApplicationRepository;
import com.deciploy.backend.modules.api.application.repository.ApplicationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class ApplicationService {
    private final DateFormat dateFormat;
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private ApplicationTypeRepository applicationTypeRepository;

    public ApplicationService() {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.ENGLISH);
    }

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

    public List<ApplicationUsage> applicationUsages(UsageFilter filter) {
        try {
            if (filter.from().isPresent() && filter.to().isPresent()) {
                Date fromDate = dateFormat.parse(filter.from().get());
                Date toDate = dateFormat.parse(filter.to().get());
                return applicationRepository.getApplicationUsage(fromDate, toDate);
            } else if (filter.from().isPresent()) {
                Date fromDate = dateFormat.parse(filter.from().get());
                return applicationRepository.getApplicationUsage(fromDate, true);
            } else if (filter.to().isPresent()) {
                Date toDate = dateFormat.parse(filter.to().get());
                return applicationRepository.getApplicationUsage(toDate, false);
            } else {
                return applicationRepository.getApplicationUsage();
            }
        } catch (ParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format");
        }
    }
}
