package com.staff.staffmanagement.entity.enums;

import lombok.Getter;

@Getter
public enum StaffTitle {
    MANAGER("MANAGER"),
    CASHIER("CASHIER"),
    PROJECTIONIST("PROJECTIONIST"),
    JANITOR("JANITOR"),
    DEFAULT_EMPLOYEE("DEFAULT_EMPLOYEE");


    private final String title;

    StaffTitle(String title) {
        this.title = title;
    }

}
