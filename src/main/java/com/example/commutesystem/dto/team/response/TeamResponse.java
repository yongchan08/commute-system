package com.example.commutesystem.dto.team.response;

import com.example.commutesystem.domain.employee.Employee;
import com.example.commutesystem.domain.employee.Role;
import com.example.commutesystem.domain.team.Team;

import java.util.List;

public class TeamResponse {

    private String name;

    private String manager;

    private int memberCount;

    public TeamResponse(Team team) {
        this.name = team.getName();

        this.manager = team.getEmployeeList().stream()
                .filter(e -> e.getRole() == Role.MANAGER)
                .map(Employee::getName)
                .findFirst()
                .orElse(null);

        this.memberCount = team.getEmployeeList().size();
    }

    public String getName() {
        return name;
    }

    public String getManager() {
        return manager;
    }

    public int getMemberCount() {
        return memberCount;
    }
}
