package com.example.commutesystem.service.commute;

import com.example.commutesystem.domain.commute.Commute;
import com.example.commutesystem.domain.dayoff.Dayoff;
import com.example.commutesystem.domain.employee.Employee;
import java.time.YearMonth;
import com.example.commutesystem.dto.commute.response.CommuteDetailResponse;
import com.example.commutesystem.dto.commute.response.DailyDetail;
import com.example.commutesystem.dto.commute.response.OvertimeResponse;
import com.example.commutesystem.repository.commute.CommuteRepository;
import com.example.commutesystem.repository.dayoff.DayoffRepository;
import com.example.commutesystem.repository.employee.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CommuteService {

    private final CommuteRepository commuteRepository;
    private final EmployeeRepository employeeRepository;
    private final DayoffRepository dayoffRepository;

    public CommuteService(CommuteRepository commuteRepository, EmployeeRepository employeeRepository, DayoffRepository dayoffRepository) {
        this.commuteRepository = commuteRepository;
        this.employeeRepository = employeeRepository;
        this.dayoffRepository = dayoffRepository;
    }

    @Transactional
    public void startWork(long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 직원입니다."));

        if (commuteRepository.existsByEmployeeAndDate(employee, LocalDate.now())) {
            throw new IllegalArgumentException("이미 출근 처리된 직원입니다.");
        }

        commuteRepository.save(new Commute(employee, LocalDateTime.now()));
    }

    @Transactional
    public void endWork(long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 직원입니다."));

        Commute commute = commuteRepository.findByEmployeeAndDateAndWorkEndTimeIsNull(employee, LocalDate.now())
                .orElseThrow(() -> new IllegalArgumentException("출근 기록이 없거나 이미 퇴근 처리되었습니다."));

        commute.endWork(LocalDateTime.now());
    }

    @Transactional(readOnly = true)
    public CommuteDetailResponse getCommuteDetails(long employeeId, YearMonth yearMonth) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 직원입니다."));

        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();
        List<LocalDate> allDatesInMonth = startDate.datesUntil(endDate.minusDays(1)).toList();

        Map<LocalDate, Commute> commuteMap = commuteRepository.findByEmployeeAndDateBetweenOrderByDateAsc(employee, startDate, endDate).stream()
                .collect(Collectors.toMap(Commute::getDate, Function.identity()));
        Set<LocalDate> dayoffDates = dayoffRepository.findByEmployeeAndDateBetweenOrderByDateAsc(employee, startDate, endDate).stream()
                .map(Dayoff::getDate)
                .collect(Collectors.toSet());

        List<DailyDetail> dailyDetailList = new ArrayList<>();
        long sum = 0;
        for (LocalDate date : allDatesInMonth) {
            if (dayoffDates.contains(date)) {
                dailyDetailList.add(new DailyDetail(date, 0, true));
            } else {
                Commute commute = commuteMap.get(date);
                if (commute != null) {
                    dailyDetailList.add(new DailyDetail(commute));
                    sum += commute.getWorkingMinutes();
                }
            }
        }

        return new CommuteDetailResponse(dailyDetailList, sum);
    }

    public List<OvertimeResponse> getOvertime(YearMonth yearMonth) {
        List<Employee> employeeList = employeeRepository.findAll();

        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();
        List<LocalDate> allDatesInMonth = startDate.datesUntil(endDate.minusDays(1)).toList();
        long totalWorkingDays = 0;
        for (LocalDate date : allDatesInMonth) {
            if (date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY) {
                totalWorkingDays += 1;
            }
        }
        long totalWorkingMinutes = totalWorkingDays * 8;

        return employeeList.stream()
                .map(e -> {
                    Long sum = commuteRepository.sumWorkingMinutes(e, startDate, endDate);
                    if (sum != null && sum > totalWorkingMinutes) {
                        return new OvertimeResponse(e.getId(), e.getName(), sum - totalWorkingMinutes);
                    } else {
                        return new OvertimeResponse(e.getId(), e.getName(), 0);
                    }
                }).collect(Collectors.toList());
    }
}
