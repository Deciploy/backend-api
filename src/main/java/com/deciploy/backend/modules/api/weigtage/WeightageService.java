package com.deciploy.backend.modules.api.weigtage;

import com.deciploy.backend.modules.api.application.ApplicationService;
import com.deciploy.backend.modules.api.application.entity.ApplicationType;
import com.deciploy.backend.modules.api.team.TeamService;
import com.deciploy.backend.modules.api.team.entity.Team;
import com.deciploy.backend.modules.api.weigtage.dto.TeamWeightage;
import com.deciploy.backend.modules.api.weigtage.dto.WeightageSaveRequest;
import com.deciploy.backend.modules.api.weigtage.entity.Weightage;
import com.deciploy.backend.modules.api.weigtage.repository.WeightageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeightageService {
    @Autowired
    private WeightageRepository weightageRepository;

    @Autowired
    private TeamService teamService;

    @Autowired
    private ApplicationService applicationService;

    public void save(WeightageSaveRequest request) {
        Team team = teamService.getById(request.teamId());
        ApplicationType applicationType = applicationService.getApplicationTypeFindById(request.applicationTypeId()).orElseThrow();

        Weightage weightage = Weightage.builder()
                .team(team)
                .applicationType(applicationType)
                .weightage(request.weightage())
                .build();

        weightageRepository.create(weightage);
    }

    public void save(List<WeightageSaveRequest> requests) {
        for (WeightageSaveRequest request : requests) {
            save(request);
        }
    }

    public List<TeamWeightage> getTeamWeightages() {
        List<Team> teams = teamService.getAll();

        return teams.stream()
                .map(team -> new TeamWeightage(team, getWeightagesByTeam(team))
                )
                .toList();
    }

    private List<Weightage> getWeightagesByTeam(Team team) {
        List<ApplicationType> applicationTypes = applicationService.getApplicationTypes();

        return applicationTypes.stream()
                .map(applicationType -> weightageRepository
                        .findWeightageByApplicationTypeAndTeam(applicationType, team)
                        .orElse(
                                Weightage.builder()
                                        .team(team)
                                        .applicationType(applicationType)
                                        .weightage(0)
                                        .build()
                        )
                )
                .toList();
    }


}
