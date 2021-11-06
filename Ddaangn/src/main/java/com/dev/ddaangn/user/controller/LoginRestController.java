package com.dev.ddaangn.user.controller;


import com.dev.ddaangn.common.api.ApiResponse;
import com.dev.ddaangn.evaluation.dto.request.EvaluationInsertRequest;
import com.dev.ddaangn.post.dto.response.PostDetailResponse;
import com.dev.ddaangn.user.config.auth.LoginUser;
import com.dev.ddaangn.user.config.auth.dto.SessionUser;
import com.dev.ddaangn.user.dto.request.UserInsertRequestDto;
import com.dev.ddaangn.user.dto.request.UserUpdateRequestDto;
import com.dev.ddaangn.user.dto.response.UserDetailResponse;
import com.dev.ddaangn.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value ="/api/v1/member")
public class LoginRestController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<UserDetailResponse> insert(@RequestBody UserInsertRequestDto request, @LoginUser SessionUser user) {
            return ApiResponse.ok(userService.insert(request));
    }

    // id로 조회
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<UserDetailResponse> getOne(@PathVariable Long id, @LoginUser SessionUser user) {
//            return ApiResponse.ok(userService.findById(id));
        return ApiResponse.ok(userService.findById(user.getId()));
    }

    // 전체 조회
    @GetMapping
    public ApiResponse<Page<UserDetailResponse>> getAll(Pageable pageable) {
        return ApiResponse.ok(userService.findAll(pageable));
    }



//     id로 Name, Picture 수정
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<UserDetailResponse> update(@PathVariable Long id, UserUpdateRequestDto userUpdateRequestDto, @LoginUser SessionUser user) {
        return ApiResponse.ok(userService.update(user.getId(),userUpdateRequestDto));
    }


    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id,@LoginUser SessionUser user) {
        userService.delete(user.getId());
        return ApiResponse.ok(null);
    }


}
