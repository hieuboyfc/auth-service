package com.zimji.auth.utils;

public class Constants {

    public static final String SYSTEM = "system";

    public static class Action {
        private Action() {
        }

        public static final String CREATE = "CREATE";
        public static final String UPDATE = "UPDATE";
        public static final String DELETE = "DELETE";
        public static final String VIEW = "VIEW";
    }

    public static class Symbol {
        private Symbol() {
        }

        public static final String COMMA = ",";
        public static final String ASTERISK = "*";
        public static final String SPACE = " ";
        public static final String UNDERSCORE = "_";
        public static final String QUESTION_MARK = "?";
        public static final String SLASH = "/";
        public static final String SHARP = "#";
        public static final String NULL = "NULL";
        public static final String DOT_SPACE = ". ";
        public static final String EMPTY = "";
        public static final String DASH = " - ";
        public static final String ASC = "ASC";
        public static final String DESC = "DESC";
    }

    public enum STATUS {
        DELETE(-9),
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
