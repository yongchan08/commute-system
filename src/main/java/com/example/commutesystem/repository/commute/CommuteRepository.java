package com.example.commutesystem.repository.commute;

import com.example.commutesystem.domain.commute.Commute;
import com.example.commutesystem.domain.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CommuteRepository extends JpaRepository<Commute, Long> {

    boolean existsByEmployeeAndDate(Employee employee, LocalDate date);

    Optional<Commute> findByEmployeeAndDateAndWorkEndTimeIsNull(Employee employee, LocalDate date);

    List<Commute> findByEmployeeAndDateBetweenOrderByDateAsc(Employee employee, LocalDate startDate, LocalDate endDate);

    @Query("SELECT SUM(c.workingMinutes) FROM Commute c WHERE c.employee = :employee AND c.date BETWEEN :start AND :end")
    Long sumWorkingMinutes(Employee employee, LocalDate start, LocalDate end);
}
