package com.example.commutesystem.service.dayoff;

import com.example.commutesystem.domain.dayoff.Dayoff;
import com.example.commutesystem.domain.employee.Employee;
import com.example.commutesystem.dto.dayoff.request.DayoffRequest;
import com.example.commutesystem.repository.dayoff.DayoffRepository;
import com.example.commutesystem.repository.employee.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DayoffService {

    private final DayoffRepository dayoffRepository;
    private final EmployeeRepository employeeRepository;

    public DayoffService(DayoffRepository dayoffRepository, EmployeeRepository employeeRepository) {
        this.dayoffRepository = dayoffRepository;
        this.employeeRepository = employeeRepository;
    }

    public void requestDayoff(long employeeId, DayoffRequest request) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 직원입니다."));

        if (employee.getTeam() == null) {
            throw new IllegalArgumentException("팀을 먼저 설정해야 합니다.");
        }

        int minNoticeDays = employee.getTeam().getMinNoticeDays();
        if (LocalDate.now().isAfter(request.getDate().minusDays(minNoticeDays))) {
            throw new IllegalArgumentException("연차는 최소 " + minNoticeDays + "일 전에 신청해야 합니다.");
        }

        long defaultDayoff = employee.isJoinedThisYear() ? 11 : 15;
        long usedDayoff = dayoffRepository.countByEmployee(employee);
        if (defaultDayoff - usedDayoff == 0) {
            throw new IllegalArgumentException("연차를 모두 사용하셨습니다.");
        }

        dayoffRepository.save(new Dayoff(employee, request.getDate()));
    }

    public long getRemainDayoff(long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 직원입니다."));

        long defaultDayoff = employee.isJoinedThisYear() ? 11 : 15;
        long usedDayoff = dayoffRepository.countByEmployee(employee);

        return defaultDayoff - usedDayoff;
    }
}
