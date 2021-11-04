package com.dev.ddaangn.evaluation.dto;

import com.dev.ddaangn.evaluation.role.EvaluationRole;
import com.dev.ddaangn.user.config.auth.LoginUser;
import com.dev.ddaangn.user.config.auth.dto.SessionUser;

public class EvaluationRequestDto {


    // 평가주는 사람, 평가받는 사람, 평가정보
    Long givingPerson;
    String GivenPerson;
    String evaluation;


    public void getUser(@LoginUser SessionUser sessionUser, EvaluationRole evaluationRole) {
        givingPerson = sessionUser.getId();
        this.evaluation = evaluationRole.getKey();

//        EvaluationRole excellent = evaluationRole.EXCELLENT;
    }

    // to Evalu
//    public Evaluation toEntity() {
//        return Evaluation.builder()
//
//                .build()
//    }


}
