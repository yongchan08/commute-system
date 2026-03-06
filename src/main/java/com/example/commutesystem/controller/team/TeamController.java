package com.example.commutesystem.controller.team;

import com.example.commutesystem.dto.team.request.TeamCreateRequest;
import com.example.commutesystem.dto.team.request.TeamUpdateRequest;
import com.example.commutesystem.dto.team.response.TeamResponse;
import com.example.commutesystem.service.team.TeamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    public void createTeam(@RequestBody TeamCreateRequest request) {
        teamService.createTeam(request);
    }

    @GetMapping
    public List<TeamResponse> getTeams() {
        return teamService.getTeams();
    }

    @PutMapping("/{teamId}/name")
    public void updateTeam(@PathVariable long teamId, @RequestBody TeamUpdateRequest request) {
        teamService.updateTeam(teamId, request);
    }

}
