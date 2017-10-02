package com.narendra.sams.web.restws.enquiry.vo;

import com.narendra.sams.web.restws.admission.vo.AcademicYearClassVO;
import java.util.List;

public class EnquiryFormUpdateVO {
    private List<AcademicYearClassVO> classes;
    private EnquiryFormVO enquiry;

    public List<AcademicYearClassVO> getClasses() {
        return this.classes;
    }

    public void setClasses(List<AcademicYearClassVO> classes) {
        this.classes = classes;
    }

    public EnquiryFormVO getEnquiry() {
        return this.enquiry;
    }

    public void setEnquiry(EnquiryFormVO enquiry) {
        this.enquiry = enquiry;
    }
}
