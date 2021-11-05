package com.dev.ddaangn.common.query;

import com.dev.ddaangn.evaluation.domain.EvaluationsDetail;
import com.dev.ddaangn.evaluation.repository.EvaluationsDetailRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InstanceEvaluationQuery {

    private final EvaluationsDetailRepository evaluationsDetailRepository;

    public void insert() {
        final List<EvaluationsDetail> details= Arrays.asList(
                new EvaluationsDetail("나눔을 해주셨어요",true),
                new EvaluationsDetail("상품상태가 설명한 것과 같아요",true),
                new EvaluationsDetail("상품설명이 자세해요",true),
                new EvaluationsDetail("좋은 상품을 저렴하게 판매해요",true),


                new EvaluationsDetail("이분과 다시는 거래하고 싶지않아요",false),
                new EvaluationsDetail("차에서 안내려요",false),
                new EvaluationsDetail("가격을 깍아요",false),
                new EvaluationsDetail("시간약속을 안 지켜요",false),
                new EvaluationsDetail("택배거래만 하려고 해요",false),
                new EvaluationsDetail("예약만 해놓고 거래 시간을 명확하게 알려주지 않아요",false),
                new EvaluationsDetail("너무 늦은 시간이나 새벽에 연락해요",false),
                new EvaluationsDetail("약속 장소에 나타나지 않았어요",false),
                new EvaluationsDetail("거래 직전 취소했어요",false)
                );

        List<EvaluationsDetail> saveDetail=evaluationsDetailRepository.saveAll(details);
    }


}
