package com.dev.ddaangn.user.dto;

import com.dev.ddaangn.user.User;
import lombok.Getter;

@Getter
public class ListResponseDto {

    private String name;
    private Long id;
    private String email;

    public ListResponseDto(User user) {
        this.name = user.getName();
        this.id = user.getId();
        this.email = user.getEmail();
    }
}
