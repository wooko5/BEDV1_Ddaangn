package com.dev.ddaangn.evaluation.controller;

import com.dev.ddaangn.common.api.ApiResponse;
import com.dev.ddaangn.evaluation.domain.EvaluationsDetail;
import com.dev.ddaangn.evaluation.dto.request.EvaluationInsertRequest;
import com.dev.ddaangn.evaluation.dto.response.EvaluationDetailResponse;
import com.dev.ddaangn.evaluation.dto.response.EvaluationMappingDetailResponse;
import com.dev.ddaangn.evaluation.service.EvaluationService;
import com.dev.ddaangn.user.config.auth.LoginUser;
import com.dev.ddaangn.user.config.auth.dto.SessionUser;
import com.dev.ddaangn.user.dto.ListResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
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
            return ApiResponse.ok(evaluationService.createEvaluation(request, user));
    }

//    @GetMapping("/details")
//    @ResponseStatus(HttpStatus.OK)
//    public ApiResponse<Pageable<EvaluationDetailResponse>> findAll() {
//        return ApiResponse.ok(evaluationService.getEvaluationsDetails(Pageable pageable));
//    }

}
