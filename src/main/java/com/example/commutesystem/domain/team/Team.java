package com.example.commutesystem.domain.team;

import com.example.commutesystem.domain.employee.Employee;
import com.example.commutesystem.domain.employee.Role;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Column(nullable = false, unique = true, length = 25)
    private String name;

    @OneToMany(mappedBy = "team")
    private List<Employee> employeeList = new ArrayList<>();

    @Column(name = "min_notice_days")
    private int minNoticeDays;

    public Team(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException();
        }
        this.name = name;
    }

    protected Team() {}

    public String getName() {
        return name;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public int getMinNoticeDays() {
        return minNoticeDays;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void removeManager() {
        Employee manager = employeeList.stream()
                .filter(e -> e.getRole() == Role.MANAGER)
                .findFirst()
                .orElse(null);

        if (manager != null) {
            manager.updateTeam(null);
        }
    }

    public void updateMinNoticeDays(int minNoticeDays) {
        this.minNoticeDays = minNoticeDays;
    }
}
