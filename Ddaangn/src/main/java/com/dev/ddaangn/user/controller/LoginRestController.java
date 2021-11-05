package com.dev.ddaangn.user.controller;


import com.dev.ddaangn.common.api.ApiResponse;
import com.dev.ddaangn.evaluation.dto.request.EvaluationInsertRequest;
import com.dev.ddaangn.user.config.auth.LoginUser;
import com.dev.ddaangn.user.config.auth.dto.SessionUser;
import com.dev.ddaangn.user.dto.request.UserInsertRequestDto;
import com.dev.ddaangn.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value ="/api/v1")
public class LoginRestController {

    private final UserService userService;


    // Oauth 2.0을 통한 유저 저장
    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<String> insert(@RequestBody UserInsertRequestDto request, @LoginUser SessionUser user) {
        if (user!=null) {
            return ApiResponse.ok(userService.insert(request));
        }
        return ApiResponse.fail(userService.insert(request));
    }




}
