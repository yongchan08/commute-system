package com.example.commutesystem.repository.team;

import com.example.commutesystem.domain.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("SELECT t FROM Team t LEFT JOIN FETCH t.employeeList")
    List<Team> findAllWithEmployeeList();
}
