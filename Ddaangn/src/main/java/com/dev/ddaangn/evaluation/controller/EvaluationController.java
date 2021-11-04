package com.dev.ddaangn.evaluation.controller;

import com.dev.ddaangn.common.api.ApiResponse;
import com.dev.ddaangn.evaluation.dto.EvaluationRequestDto;
import com.dev.ddaangn.evaluation.dto.EvaluationResponseDto;
import com.dev.ddaangn.evaluation.service.EvaluationService;
import com.dev.ddaangn.user.config.auth.LoginUser;
import com.dev.ddaangn.user.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value ="/api/v1")
public class EvaluationController {
    // 사용자 평가에 의해 온도가 수정되어야 한다.
    EvaluationService evaluationService;

    @PutMapping("/temperature/evaluation")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<EvaluationResponseDto> insert(EvaluationRequestDto evaluationRequestDto, @LoginUser SessionUser user) {
        if (user!=null) {
            return ApiResponse.ok(evaluationService.update(evaluationRequestDto, user.getId()));
        }
        return ApiResponse.fail(evaluationService.update(evaluationRequestDto,user.getId()));
    }

}
