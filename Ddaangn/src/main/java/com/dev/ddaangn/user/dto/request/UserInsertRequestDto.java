package com.dev.ddaangn.user.dto.request;

import com.dev.ddaangn.evaluation.domain.Evaluation;
import com.dev.ddaangn.user.User;
import com.dev.ddaangn.user.role.LoginRole;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Getter
@Builder
public class UserInsertRequestDto {

    private String name;
    private String email;
    private String picture;
    private LoginRole role;

    // requestDto -> Entity
    public User insertRequestDtoToEntity(UserInsertRequestDto requestDto) {
        return User.builder()
                .name(requestDto.getName())
                .email(requestDto.getEmail())
                .picture(requestDto.getPicture())
                .role(requestDto.getRole())
                .build();
    }
}
