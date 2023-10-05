package com.staff.staffmanagement.entity.enums;

public enum StaffTitle {
    MANAGER("MANAGER"),
    CASHIER("CASHIER"),
    PROJECTIONIST("PROJECTIONIST"),
    JANITOR("JANITOR"),
    DEFAULT_EMPLOYEE("DEFAULT_EMPLOYEE");


    private String title;

    StaffTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
