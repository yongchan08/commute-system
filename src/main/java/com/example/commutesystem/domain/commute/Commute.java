package com.example.commutesystem.domain.commute;

import com.example.commutesystem.domain.employee.Employee;
import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Commute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(nullable = false)
    private LocalDate date;

    @Column(name = "work_start_time", nullable = false)
    private LocalDateTime workStartTime;

    @Column(name = "work_end_time")
    private LocalDateTime workEndTime;

    @Column(name = "working_minutes")
    private Long workingMinutes;

    public Commute(Employee employee, LocalDateTime workStartTime) {
        this.employee = employee;
        this.date = workStartTime.toLocalDate();
        this.workStartTime = workStartTime;
    }

    protected Commute() {}

    public void endWork(LocalDateTime workEndTime) {
        if (this.workStartTime == null) {
            throw new IllegalArgumentException();
        }
        if (workEndTime.isBefore(this.workStartTime)) {
            throw new IllegalArgumentException();
        }
        this.workEndTime = workEndTime;
        this.workingMinutes = Duration.between(this.workStartTime, this.workEndTime).toMinutes();
    }

    public LocalDate getDate() {
        return date;
    }

    public Long getWorkingMinutes() {
        return workingMinutes;
    }
}
