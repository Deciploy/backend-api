package com.deciploy.backend.modules.api.team;

import com.deciploy.backend.modules.api.auth.AuthService;
import com.deciploy.backend.modules.api.company.entity.Company;
import com.deciploy.backend.modules.api.team.dto.CreateTeamRequest;
import com.deciploy.backend.modules.api.team.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TeamService {
    @Autowired
    private AuthService authService;

    @Autowired
    private TeamRepository teamRepository;


    public void create(CreateTeamRequest createTeamRequest) {
        Company company = authService.getAuthenticatedUser().getCompany();

        if (teamRepository.findByNameAndCompany(createTeamRequest.name(), company).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Team with this name already exists");
        }

        Team team = new Team();
        team.setName(createTeamRequest.name());
        team.setDescription(createTeamRequest.description());
        team.setCompany(company);
        teamRepository.save(team);
    }

    public List<Team> getAll() {
        return teamRepository.findAllByCompany(authService.getAuthenticatedUser().getCompany());
    }


}
