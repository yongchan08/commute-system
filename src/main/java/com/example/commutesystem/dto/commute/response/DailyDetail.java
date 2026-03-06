package com.example.commutesystem.dto.commute.response;

import com.example.commutesystem.domain.commute.Commute;

import java.time.LocalDate;

public class DailyDetail {

    private LocalDate date;

    private Long workingMinutes;

    private boolean usingDayOff = false;

    public DailyDetail(Commute commute) {
        this.date = commute.getDate();
        this.workingMinutes = commute.getWorkingMinutes();
    }

    public DailyDetail(LocalDate date, long workingMinutes, boolean usingDayOff) {
        this.date = date;
        this.workingMinutes = workingMinutes;
        this.usingDayOff = usingDayOff;
    }

    public LocalDate getDate() {
        return date;
    }

    public Long getWorkingMinutes() {
        return workingMinutes;
    }

    public boolean getUsingDayoff() {
        return usingDayOff;
    }
}
