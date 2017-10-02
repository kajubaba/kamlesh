package com.narendra.sams.admission.literals;

public interface AdmissionLiteral {
    public static final String ADMISSION_TYPE_NEW = "New";
    public static final Short ADMISSION_TYPE_NEW_DB_ID = Short.valueOf(Integer.valueOf(1).shortValue());
    public static final String ADMISSION_TYPE_REGULAR = "Regular";
    public static final Short ADMISSION_TYPE_REGULAR_DB_ID = Short.valueOf(Integer.valueOf(2).shortValue());
    public static final Long TEST_COURSE_YEAR_ID = Long.valueOf(1);
}
