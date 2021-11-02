package com.dev.ddaangn.evaluation.dto.response;

import com.dev.ddaangn.common.dto.BaseResponse;
import com.dev.ddaangn.evaluation.domain.EvaluationMappingDetail;
import lombok.Getter;

@Getter
public class EvaluationMappingDetailResponse extends BaseResponse {
    private final Long id;
    private final Long evaluationId;
    private final Long evaluationDetail;

    public EvaluationMappingDetailResponse(EvaluationMappingDetail item) {
        super(item.getCreatedAt(), item.getUpdateAt(), item.getDeletedAt());
        id = item.getId();
        evaluationId = item.getEvaluation().getId();
        evaluationDetail = item.getEvaluationsDetail().getId();
    }
}
