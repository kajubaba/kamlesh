package com.narendra.sams.web.restws.academics.utils;

import com.narendra.sams.academics.domain.AssessmentSubject;
import java.util.ArrayList;
import java.util.List;

public class MarksheetUtil {
    public List<MarkSheetTemplateCCE> getMarksheetForTerm1Only(List<AssessmentSubject> assessmentSubjects) {
        ArrayList<MarkSheetTemplateCCE> dataBeanList = new ArrayList();
        for (AssessmentSubject assessmentSubject : assessmentSubjects) {
            dataBeanList.add(prepareMarskheetDSForPdfNew(assessmentSubject.getName(), (String) assessmentSubject.getGrades().get(0), (String) assessmentSubject.getGrades().get(1), (String) assessmentSubject.getGrades().get(2), (String) assessmentSubject.getGrades().get(3)));
        }
        return dataBeanList;
    }

    private MarkSheetTemplateCCE prepareMarskheetDSForPdfNew(String subject, String FA1, String FA2, String SA1, String term1Sum) {
        MarkSheetTemplateCCE dataBean = new MarkSheetTemplateCCE();
        dataBean.setSubject(subject);
        dataBean.setFA1(FA1);
        dataBean.setFA2(FA2);
        dataBean.setSA1(SA1);
        dataBean.setTerm1Sum(term1Sum);
        dataBean.setFA3(FA1);
        dataBean.setFA4(FA2);
        dataBean.setSA2(SA1);
        return dataBean;
    }
}
