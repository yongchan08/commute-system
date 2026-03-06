package com.example.commutesystem.dto.employee.request;

import com.example.commutesystem.domain.employee.Role;

import java.time.LocalDate;

public class EmployeeCreateRequest {

    private String name;

    private Role role;

    private LocalDate birthday;

    private LocalDate workStartDate;

    public String getName() {
        return name;
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
