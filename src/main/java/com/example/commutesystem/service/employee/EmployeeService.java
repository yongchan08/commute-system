package com.example.commutesystem.service.employee;

import com.example.commutesystem.domain.employee.Employee;
import com.example.commutesystem.domain.team.Team;
import com.example.commutesystem.dto.employee.request.EmployeeCreateRequest;
import com.example.commutesystem.dto.employee.request.EmployeeTeamUpdateRequest;
import com.example.commutesystem.dto.employee.request.EmployeeUpdateRequest;
import com.example.commutesystem.dto.employee.response.EmployeeResponse;
import com.example.commutesystem.repository.employee.EmployeeRepository;
import com.example.commutesystem.repository.team.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final TeamRepository teamRepository;

    public EmployeeService(EmployeeRepository employeeRepository, TeamRepository teamRepository) {
        this.employeeRepository = employeeRepository;
        this.teamRepository = teamRepository;
    }

    @Transactional
    public void createEmployee(EmployeeCreateRequest request) {
        employeeRepository.save(new Employee(request.getName(), request.getRole(), request.getBirthday(), request.getWorkStartDate()));
    }

    @Transactional(readOnly = true)
    public List<EmployeeResponse> getEmployees() {
        return employeeRepository.findAllWithTeam().stream()
                .map(EmployeeResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateEmployee(Long employeeId, EmployeeUpdateRequest request) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 직원입니다."));

        if (request.getName() != null && !request.getName().isBlank()) {
            employee.updateName(request.getName());
        }

        if (request.getBirthday() != null) {
            employee.updateBirthday(request.getBirthday());
        }

        if (request.getWorkStartDate() != null) {
            employee.updateWorkStartDate(request.getWorkStartDate());
        }
    }

    @Transactional
    public void updateEmployeeTeam(long employeeId, EmployeeTeamUpdateRequest request) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 직원입니다."));

        Team team = teamRepository.findById(request.getTeamId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 팀입니다."));

        employee.updateTeam(team);
    }
}
