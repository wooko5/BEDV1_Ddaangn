package com.dev.ddaangn.post.domain;

import com.dev.ddaangn.common.error.ErrorMessage;

import java.util.Arrays;

public enum PostStatus {
    RESERVED(1, "예약 중"),
    SOLD(2, "판매 완료"),
    SELLING(3, "판매 중"),
    HIDE(4, "숨김");

    private final String status;
    private final int val;

    PostStatus(int val, String status) {
        this.val = val;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public Integer getValue() {
        return val;
    }

    public static PostStatus of(String status) {
        return Arrays.stream(values())
                .filter(element -> element.status.equals(status))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(ErrorMessage.INTERNAL_SERVER_ERROR.message()));
    }

    public static PostStatus of(Integer value) {
        return Arrays.stream(values())
                .filter(element -> element.val == value)
                .findFirst()
                .orElseThrow(() -> new RuntimeException(ErrorMessage.INTERNAL_SERVER_ERROR.message()));
    }
}
