package com.example.commutesystem.repository.dayoff;

import com.example.commutesystem.domain.dayoff.Dayoff;
import com.example.commutesystem.domain.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DayoffRepository extends JpaRepository<Dayoff, Long> {

    long countByEmployee(Employee employee);

    List<Dayoff> findByEmployeeAndDateBetweenOrderByDateAsc(Employee employee, LocalDate startDate, LocalDate endDate);
}
