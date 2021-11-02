package com.dev.ddaangn.evaluation.role;

import com.dev.ddaangn.common.error.ErrorMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum EvaluationStatus {

    EXCELLENT(1, "매우 만족"),
    GOOD(2, "만족"),
    BAD(3, "불만");

    private final String status;
    private final int val;

    EvaluationStatus(int val, String status) {
        this.val = val;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public Integer getValue() {
        return val;
    }

    public static EvaluationStatus of(String status) {
        return Arrays.stream(values())
                .filter(element -> element.status.equals(status))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(ErrorMessage.INTERNAL_SERVER_ERROR.message()));
    }

    public static EvaluationStatus of(Integer value) {
        return Arrays.stream(values())
                .filter(element -> element.val == value)
                .findFirst()
                .orElseThrow(() -> new RuntimeException(ErrorMessage.INTERNAL_SERVER_ERROR.message()));
    }



}
