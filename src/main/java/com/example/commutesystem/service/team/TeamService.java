package com.example.commutesystem.service.team;

import com.example.commutesystem.domain.team.Team;
import com.example.commutesystem.dto.team.request.TeamCreateRequest;
import com.example.commutesystem.dto.team.request.TeamUpdateRequest;
import com.example.commutesystem.dto.team.response.TeamResponse;
import com.example.commutesystem.repository.team.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Transactional
    public void createTeam(TeamCreateRequest request) {
        teamRepository.save(new Team(request.getName()));
    }

    @Transactional(readOnly = true)
    public List<TeamResponse> getTeams() {
        return teamRepository.findAllWithEmployeeList().stream()
                .map(TeamResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateTeam(long teamId, TeamUpdateRequest request) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 팀입니다."));

        if (request.getName() != null && !request.getName().isBlank()) {
            team.updateName(request.getName());
        }

        if (request.getMinNoticeDays() != null) {
            team.updateMinNoticeDays(request.getMinNoticeDays());
        }
    }
}
