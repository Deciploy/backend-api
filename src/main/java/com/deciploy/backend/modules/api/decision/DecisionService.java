package com.deciploy.backend.modules.api.decision;

import com.deciploy.backend.modules.api.auth.AuthService;
import com.deciploy.backend.modules.api.decision.dto.EmployeeWorkTime;
import com.deciploy.backend.modules.api.decision.dto.WorkTimeFilter;
import com.deciploy.backend.modules.api.time.repository.TimeRepository;
import com.deciploy.backend.modules.api.user.UserRepository;
import com.deciploy.backend.modules.api.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

@Service
public class DecisionService {
    private final DateFormat dateFormat;
    @Autowired
    private TimeRepository timeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthService authService;

    public DecisionService() {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.ENGLISH);
    }

    public List<EmployeeWorkTime> getAllEmployeesWorkTimes(WorkTimeFilter filter) {
        List<User> employees = userRepository
                .findUsersByCompany(authService.getAuthenticatedUser().getCompany());

        if (filter.team().isPresent()) {
            employees = employees.stream()
                    .filter(employee -> employee.getTeam().getId().equals(filter.team().get()))
                    .toList();
        }

        if (filter.from().isPresent() && filter.to().isEmpty()) {
            return employees.stream()
                    .map(employee -> {
                        try {
                            return new EmployeeWorkTime(employee, timeRepository.getWorkTimeByEmployee(employee, dateFormat.parse(filter.from().get()), true));
                        } catch (ParseException e) {
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format");
                        }
                    })
                    .toList();
        }


        if (filter.from().isEmpty() && filter.to().isPresent()) {
            return employees.stream()
                    .map(employee -> {
                        try {
                            return new EmployeeWorkTime(employee, timeRepository.getWorkTimeByEmployee(employee, dateFormat.parse(filter.to().get()), false));
                        } catch (ParseException e) {
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format");
                        }
                    })
                    .toList();
        }

        if (filter.from().isPresent() && filter.to().isPresent()) {
            return employees.stream()
                    .map(employee -> {
                        try {
                            return new EmployeeWorkTime(employee, timeRepository.getWorkTimeByEmployee(employee, dateFormat.parse(filter.from().get()), dateFormat.parse(filter.to().get())));
                        } catch (ParseException e) {
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format");
                        }
                    })
                    .toList();
        }

        return employees.stream()
                .map(employee -> new EmployeeWorkTime(employee, timeRepository.getWorkTimeByEmployee(employee)))
                .toList();
    }
}
