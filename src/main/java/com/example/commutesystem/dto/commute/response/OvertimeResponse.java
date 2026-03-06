package com.example.commutesystem.dto.commute.response;

public class OvertimeResponse {

    private long id;

    private String name;

    private long overtimeMinutes;

    public OvertimeResponse(long id, String name, long overtimeMinutes) {
        this.id = id;
        this.name = name;
        this.overtimeMinutes = overtimeMinutes;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getOvertimeMinutes() {
        return overtimeMinutes;
    }

}
