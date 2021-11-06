package com.dev.ddaangn.user.dto.response;

import com.dev.ddaangn.common.BaseEntity;
import com.dev.ddaangn.common.dto.BaseResponse;
import com.dev.ddaangn.post.domain.Post;
import com.dev.ddaangn.user.User;
import com.dev.ddaangn.user.dto.request.UserInsertRequestDto;
import com.dev.ddaangn.user.role.LoginRole;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import java.util.Objects;

@EqualsAndHashCode
@Getter
public class UserDetailResponse extends BaseResponse {


    private String name;
    private String email;
    private String picture;
    private LoginRole role;
    private Double temperature;
    private String address;
    private String phoneNumber;


    public UserDetailResponse(User user) {
        super(user.getCreatedAt(), user.getUpdateAt(), user.getDeletedAt());
        name = user.getName();
        email = user.getEmail();
        picture = user.getPicture();
        role = user.getRole();
        temperature = user.getTemperature();
        address = user.getAddress();
        phoneNumber = user.getPhoneNumber();
    }

}
