package com.dev.ddaangn.user.dto.request;

import com.dev.ddaangn.evaluation.domain.Evaluation;
import com.dev.ddaangn.user.User;
import com.dev.ddaangn.user.role.LoginRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
@AllArgsConstructor
@Getter
@Builder
public class UserInsertRequestDto {


    private String name;
    private String email;
    private String picture;
    private LoginRole role;
    private Double temperature;
    private String address;
    private String phoneNumber;


    // requestDto -> Entity
    public User insertRequestDtoToEntity(UserInsertRequestDto requestDto) {
        return User.builder()
                .name(requestDto.getName())
                .email(requestDto.getEmail())
                .picture(requestDto.getPicture())
                .role(requestDto.getRole())
                .temperature(requestDto.getTemperature())
                .address(requestDto.getAddress())
                .phoneNumber(requestDto.getPhoneNumber())
                .build();
    }
}
