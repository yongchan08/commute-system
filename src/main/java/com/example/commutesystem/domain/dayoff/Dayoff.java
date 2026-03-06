package com.example.commutesystem.domain.dayoff;

import com.example.commutesystem.domain.employee.Employee;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Dayoff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(nullable = false)
    private LocalDate date;

    public Dayoff(Employee employee, LocalDate date) {
        this.employee = employee;
        this.date = date;
    }

    protected Dayoff() {}

    public LocalDate getDate() {
        return date;
    }
}
