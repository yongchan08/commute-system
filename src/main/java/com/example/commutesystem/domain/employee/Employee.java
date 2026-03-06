package com.example.commutesystem.domain.employee;

import com.example.commutesystem.domain.team.Team;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Column(nullable = false, length = 25)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(name = "work_start_date", nullable = false)
    private LocalDate workStartDate;

    public Employee(String name, Role role, LocalDate birthday, LocalDate workStartDate) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름은 필수 입력 항목입니다.");
        }
        if (role == null) {
            throw new IllegalArgumentException("매니저 여부는 필수 입력 항목입니다.");
        }
        if (birthday == null) {
            throw new IllegalArgumentException("생년월일은 필수 입력 항목입니다.");
        }
        if (workStartDate == null) {
            throw new IllegalArgumentException("입사일은 필수 입력 항목입니다.");
        }
        this.name = name;
        this.role = role;
        this.birthday = birthday;
        this.workStartDate = workStartDate;
    }

    protected Employee() {}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Team getTeam() {
        return team;
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

    public void updateName(String name) {
        this.name = name;
    }

    public void updateTeam(Team team) {
        if(this.team != null) {
            this.team.getEmployeeList().remove(this);
        }

        if (this.role == Role.MANAGER && team != null) {
            team.removeManager();
        }

        this.team = team;
        if (team != null) {
            this.team.getEmployeeList().add(this);
        }
    }

    public void updateBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void updateWorkStartDate(LocalDate workStartDate) {
        this.workStartDate = workStartDate;
    }

    public boolean isJoinedThisYear() {
        return workStartDate.getYear() == LocalDate.now().getYear();
    }

}
