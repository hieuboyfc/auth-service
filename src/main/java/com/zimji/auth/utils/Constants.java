package com.zimji.auth.utils;

public class Constants {

    public static final String SYSTEM = "system";

    public enum STATUS {
        INACTIVE(0),
        ACTIVE(1);

        private final int value;

        STATUS(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }

}
