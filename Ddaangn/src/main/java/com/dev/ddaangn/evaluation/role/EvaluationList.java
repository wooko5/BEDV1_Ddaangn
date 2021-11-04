package com.dev.ddaangn.evaluation.role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public enum EvaluationList {
    ;
//    EXCELLENT("매우 만족", "나눔을 해주셨어요","")

    private final Map<EvaluationStatus, ArrayList<String>> evaluation=new HashMap<>();

    EvaluationList() {
        evaluation.get(EvaluationStatus.EXCELLENT).add("나눔을 해주셨어요");
        evaluation.get(EvaluationStatus.EXCELLENT).add("상품상태가 설명한 것과 같아요");
        evaluation.get(EvaluationStatus.EXCELLENT).add("상품설명이 자세해요");
        evaluation.get(EvaluationStatus.EXCELLENT).add("좋은 상품을 저렴하게 판매해요");
        evaluation.get(EvaluationStatus.EXCELLENT).add("시간약속을 잘 지켜요");
        evaluation.get(EvaluationStatus.EXCELLENT).add("응답이 빨라요");
        evaluation.get(EvaluationStatus.EXCELLENT).add("친절하고 매너가 좋아요");

        evaluation.get(EvaluationStatus.GOOD).add("나눔을 해주셨어요");
        evaluation.get(EvaluationStatus.GOOD).add("상품상태가 설명한 것과 같아요");
        evaluation.get(EvaluationStatus.GOOD).add("상품설명이 자세해요");
        evaluation.get(EvaluationStatus.GOOD).add("좋은 상품을 저렴하게 판매해요");
        evaluation.get(EvaluationStatus.GOOD).add("시간약속을 잘 지켜요");
        evaluation.get(EvaluationStatus.GOOD).add("응답이 빨라요");
        evaluation.get(EvaluationStatus.GOOD).add("친절하고 매너가 좋아요");


        evaluation.get(EvaluationStatus.BAD).add("이분과 다시는 거래하고 싶지않아요");
        evaluation.get(EvaluationStatus.BAD).add("차에서 안내려요");
        evaluation.get(EvaluationStatus.BAD).add("가격을 깍아요");
        evaluation.get(EvaluationStatus.BAD).add("시간약속을 안 지켜요");
        evaluation.get(EvaluationStatus.BAD).add("택배거래만 하려고 해요");
        evaluation.get(EvaluationStatus.BAD).add("예약만 해놓고 거래 시간을 명확하게 알려주지 않아요");
        evaluation.get(EvaluationStatus.BAD).add("너무 늦은 시간이나 새벽에 연락해요");
        evaluation.get(EvaluationStatus.BAD).add("약속 장소에 나타나지 않았어요");
        evaluation.get(EvaluationStatus.BAD).add("거래 직전 취소했어요");


    }
}
