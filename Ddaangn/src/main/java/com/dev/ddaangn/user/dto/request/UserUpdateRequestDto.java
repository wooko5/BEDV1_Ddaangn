package com.dev.ddaangn.user.dto.request;

import com.dev.ddaangn.user.role.LoginRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class UserUpdateRequestDto {

    private String name;
    private String picture;



}
