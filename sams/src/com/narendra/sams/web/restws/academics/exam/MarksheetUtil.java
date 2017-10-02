package com.narendra.sams.web.restws.academics.exam;

import java.util.ArrayList;
import java.util.List;

public class MarksheetUtil {
    public List<MarkSheetTemplateCCE> getMarksheet() {
        ArrayList<MarkSheetTemplateCCE> dataBeanList = new ArrayList();
        dataBeanList.add(prepareMarskheetDSForPdf("1", "Hindi", "3", "4", "5", "12", "2", "3", "4", "9", "12", "30"));
        dataBeanList.add(prepareMarskheetDSForPdf("2", "English", "1", "2", "3", "6", "2", "3", "4", "9", "8", "22"));
        dataBeanList.add(prepareMarskheetDSForPdf("3", "Maths", "7", "8", "9", "24", "6", "7", "8", "21", "28", "33"));
        return dataBeanList;
    }

    private MarkSheetTemplateCCE prepareMarskheetDSForPdf(String sNo, String subject, String FA1, String FA2, String SA1, String term1Sum, String Fa3, String Fa4, String Sa2, String term2Sum, String sumAllFA, String sumAllSA) {
        MarkSheetTemplateCCE dataBean = new MarkSheetTemplateCCE();
        dataBean.setsNo(sNo);
        dataBean.setSubject(subject);
        dataBean.setFA1(FA1);
        dataBean.setFA2(FA2);
        dataBean.setSA1(SA1);
        dataBean.setTerm1Sum(term1Sum);
        dataBean.setSumAllFA(sumAllFA);
        dataBean.setFA3(FA1);
        dataBean.setFA4(FA2);
        dataBean.setSA2(SA1);
        dataBean.setTerm2Sum(term2Sum);
        dataBean.setSumAllSA(sumAllSA);
        return dataBean;
    }
}
