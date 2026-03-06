package com.example.commutesystem.controller.employee;

import com.example.commutesystem.dto.employee.request.EmployeeCreateRequest;
import com.example.commutesystem.dto.employee.request.EmployeeTeamUpdateRequest;
import com.example.commutesystem.dto.employee.request.EmployeeUpdateRequest;
import com.example.commutesystem.dto.employee.response.EmployeeResponse;
import com.example.commutesystem.service.employee.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public void createEmployee(@RequestBody EmployeeCreateRequest request) {
        employeeService.createEmployee(request);
    }

    @GetMapping
    public List<EmployeeResponse> getEmployees() {
        return employeeService.getEmployees();
    }

    @PutMapping("/{employeeId}")
    public void updateEmployee(@PathVariable long employeeId, @RequestBody EmployeeUpdateRequest request) {
        employeeService.updateEmployee(employeeId, request);
    }

    @PutMapping("/{employeeId}/team")
    public void updateEmployeeTeam(@PathVariable long employeeId, @RequestBody EmployeeTeamUpdateRequest request) {
        employeeService.updateEmployeeTeam(employeeId, request);
    }
}
