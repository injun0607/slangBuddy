package org.alham.slangbuddy.enums;


import lombok.Getter;

@Getter
public enum UserRole {
    BASIC(5,3), ADVANCED(10,5),PREMIUM(20,10);

    private final int maxSaved;
    private final int maxPermanent;

    UserRole(int maxSaved, int maxPermanent) {
        this.maxSaved = maxSaved;
        this.maxPermanent = maxPermanent;
    }

}
