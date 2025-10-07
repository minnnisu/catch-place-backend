package org.catchplace.catchplacebackend.constant;

import lombok.Getter;

@Getter
public enum UserRole {
    SERVICE_ADMIN("ROLE_SERVICE_ADMIN", "서비스 관리자"),
    VISITOR("ROLE_VISITOR", "방문자");

    private final String key;
    private final String description;

    UserRole(String key, String description) {
        this.key = key;
        this.description = description;
    }

}