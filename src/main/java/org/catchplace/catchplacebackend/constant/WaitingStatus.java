package org.catchplace.catchplacebackend.constant;

import lombok.Getter;

@Getter
public enum WaitingStatus {
    WAITING("대기중"),
    ENTERED("입장"),
    CANCELLED_BY_USER("본인에 의해 대기 취소"),
    CANCELLED_BY_OPERATOR("부스 운영자에 의해 대기 취소");

    private final String description;

    WaitingStatus(String description) {
        this.description = description;
    }

}