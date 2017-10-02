package com.narendra.sams.web.restws.transportation.form;

import com.narendra.sams.admission.domain.StudentTranslation;
import java.util.List;

public class TranslationForm {
    private List<StudentTranslation> studentTranslations;

    public List<StudentTranslation> getStudentTranslations() {
        return this.studentTranslations;
    }

    public void setStudentTranslations(List<StudentTranslation> studentTranslations) {
        this.studentTranslations = studentTranslations;
    }
}
