package com.dev.ddaangn.evaluation.controller;

import com.dev.ddaangn.common.api.ApiResponse;
import com.dev.ddaangn.evaluation.dto.request.EvaluationInsertRequest;
import com.dev.ddaangn.evaluation.dto.response.EvaluationMappingDetailResponse;
import com.dev.ddaangn.evaluation.service.EvaluationService;
import com.dev.ddaangn.user.config.auth.LoginUser;
import com.dev.ddaangn.user.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/evaluations")
public class EvaluationController {
    private final EvaluationService evaluationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<List<EvaluationMappingDetailResponse>> update(@RequestBody EvaluationInsertRequest request, @LoginUser SessionUser user) {
            log.info("[제발제발제발] id : {} ", user.getId());
            log.info("[제발제발제발] name : {} ", user.getName());
            return ApiResponse.ok(evaluationService.createEvaluation(request, user));
    }

}
