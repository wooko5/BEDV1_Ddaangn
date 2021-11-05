package com.dev.ddaangn.evaluation.dto.response;

import com.dev.ddaangn.common.dto.BaseResponse;
import com.dev.ddaangn.evaluation.domain.EvaluationMappingDetail;
import com.dev.ddaangn.evaluation.domain.EvaluationsDetail;
import lombok.Getter;

@Getter
public class EvaluationDetailResponse extends BaseResponse {
    private final Long id;
    private final String description;
    private final Boolean positive;


    public EvaluationDetailResponse(EvaluationsDetail item) {
        super(item.getCreatedAt(), item.getUpdateAt(), item.getDeletedAt());
        id = item.getId();
        description = item.getDescription();
        positive=item.getPositive();
    }

}
