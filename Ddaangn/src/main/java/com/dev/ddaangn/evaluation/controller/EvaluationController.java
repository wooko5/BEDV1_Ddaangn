package com.dev.ddaangn.evaluation.controller;

import com.dev.ddaangn.common.api.ApiResponse;
import com.dev.ddaangn.evaluation.dto.request.EvaluationRequestDto;
//import com.dev.ddaangn.evaluation.dto.response.EvaluationResponseDto;
import com.dev.ddaangn.evaluation.service.EvaluationService;
import com.dev.ddaangn.user.config.auth.LoginUser;
import com.dev.ddaangn.user.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value ="/api/v1")
public class EvaluationController {
    // 사용자 평가에 의해 온도가 수정되어야 한다.
    EvaluationService evaluationService;

    @PutMapping("/temperature/evaluation")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Long> update(@RequestBody EvaluationRequestDto evaluationRequestDto, @LoginUser SessionUser user) {
//        if (user!=null) {
            return ApiResponse.ok(evaluationService.update(evaluationRequestDto, user));
//        }

//        return ApiResponse.ok(1L);
//        return ApiResponse.fail(evaluationService.update(evaluationRequestDto,user));
    }

}
