package com.example.commutesystem.controller.dayoff;

import com.example.commutesystem.dto.dayoff.request.DayoffRequest;
import com.example.commutesystem.service.dayoff.DayoffService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employees/{employeeId}")
public class DayoffController {

    private final DayoffService dayoffService;

    public DayoffController(DayoffService dayoffService) {
        this.dayoffService = dayoffService;
    }

    @PostMapping("/dayoffs/request")
    public void requestDayoff(@PathVariable long employeeId, @RequestBody DayoffRequest request) {
        dayoffService.requestDayoff(employeeId, request);
    }

    @GetMapping("/dayoffs/remain")
    public long getRemainDayoff(@PathVariable long employeeId) {
        return dayoffService.getRemainDayoff(employeeId);
    }

}
