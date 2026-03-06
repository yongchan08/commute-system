package com.example.commutesystem.controller.commute;

import java.time.YearMonth;
import com.example.commutesystem.dto.commute.response.CommuteDetailResponse;
import com.example.commutesystem.dto.commute.response.OvertimeResponse;
import com.example.commutesystem.service.commute.CommuteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class CommuteController {

    private final CommuteService commuteService;

    public CommuteController(CommuteService commuteService) {
        this.commuteService = commuteService;
    }

    @PostMapping("/{employeeId}/commutes/start-work")
    public void startWork(@PathVariable long employeeId) {
        commuteService.startWork(employeeId);
    }

    @PutMapping("/{employeeId}/commutes/end-work")
    public void endWork(@PathVariable long employeeId) {
        commuteService.endWork(employeeId);
    }

    @GetMapping("/{employeeId}/commutes/detail")
    public CommuteDetailResponse getCommuteDetails(@PathVariable long employeeId, @RequestParam YearMonth yearMonth) {
        return commuteService.getCommuteDetails(employeeId, yearMonth);
    }

    @GetMapping("/commutes/overtime")
    public List<OvertimeResponse> getOvertime(@RequestParam YearMonth yearMonth) {
        return commuteService.getOvertime(yearMonth);
    }
}
