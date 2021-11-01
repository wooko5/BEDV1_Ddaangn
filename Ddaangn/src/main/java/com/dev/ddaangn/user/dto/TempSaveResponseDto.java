package com.dev.ddaangn.user.dto;

import com.dev.ddaangn.user.User;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
public class TempSaveResponseDto {

    private Double temperature;
    private String name;
    private Long id;
    private String email;

    // Response DTO
    public TempSaveResponseDto (User user) {
        this.id=user.getId();
        this.name=user.getName();
        this.email=user.getEmail();
        this.temperature=user.getTemperature();
    }



}
