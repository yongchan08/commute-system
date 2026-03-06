package com.example.commutesystem.dto.team.request;

import jakarta.validation.constraints.NotBlank;

public class TeamCreateRequest {

    private String name;

    public String getName() {
        return name;
    }
}
