package com.example.commutesystem.dto.employee.response;

import com.example.commutesystem.domain.employee.Employee;
import com.example.commutesystem.domain.employee.Role;
import com.example.commutesystem.domain.team.Team;

import java.time.LocalDate;

public class EmployeeResponse {

    private String name;

    private String teamName;

    private Role role;

    private LocalDate birthday;

    private LocalDate workStartDate;

    public EmployeeResponse(Employee employee) {
        this.name = employee.getName();
        this.teamName = employee.getTeam() != null ? employee.getTeam().getName() : null;
        this.role = employee.getRole();
        this.birthday = employee.getBirthday();
        this.workStartDate = employee.getWorkStartDate();
    }

    public String getName() {
        return name;
    }

    public String getTeamName() {
        return teamName;
    }

    public Role getRole() {
        return role;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public LocalDate getWorkStartDate() {
        return workStartDate;
    }
}
