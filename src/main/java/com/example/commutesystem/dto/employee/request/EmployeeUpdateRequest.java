package com.example.commutesystem.dto.employee.request;

import java.time.LocalDate;

public class EmployeeUpdateRequest {

    private String name;

    private LocalDate birthday;

    private LocalDate workStartDate;

    public String getName() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public LocalDate getWorkStartDate() {
        return workStartDate;
    }
}
