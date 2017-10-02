package com.narendra.sams.core.dao.impl;

import com.narendra.sams.core.dao.InstituteSettingDAO;
import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.AdmissionSettings;
import com.narendra.sams.core.domain.EnquirySettings;
import com.narendra.sams.core.domain.FeeSettings;
import com.narendra.sams.core.domain.InstituteSetting;
import com.narendra.sams.core.domain.UserView;
import com.narendra.sams.core.util.DateUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class InstituteSettingDAOImpl extends HibernateDaoSupport implements InstituteSettingDAO {
    public InstituteSetting getInstituteSetting(Long instituteId) {
        Criteria crt = getSession().createCriteria(InstituteSetting.class);
        crt.add(Restrictions.eq("institute.id", instituteId));
        return (InstituteSetting) crt.uniqueResult();
    }

    public void updateInstituteSetting(InstituteSetting instituteSetting, Long userId) {
        InstituteSetting persistInstituteSetting = (InstituteSetting) getHibernateTemplate().load(InstituteSetting.class, instituteSetting.getId());
        persistInstituteSetting.setModifiedDate(DateUtil.getSystemDateTime());
        persistInstituteSetting.setModifiedBy((UserView) getHibernateTemplate().load(UserView.class, userId));
        persistInstituteSetting.setIsIdGenerationMethodSame(instituteSetting.getIsIdGenerationMethodSame());
        if (instituteSetting.getAdmissionSettings() != null) {
            AdmissionSettings admissionSettings = persistInstituteSetting.getAdmissionSettings();
            if (admissionSettings == null) {
                admissionSettings = new AdmissionSettings();
            }
            if (instituteSetting.getAdmissionSettings().getActiveAcademicYear() != null) {
                admissionSettings.setActiveAcademicYear((AcademicYear) getHibernateTemplate().load(AcademicYear.class, instituteSetting.getAdmissionSettings().getActiveAcademicYear().getId()));
            } else {
                admissionSettings.setActiveAcademicYear(null);
            }
            admissionSettings.setStudentIdGenerationMethod(instituteSetting.getAdmissionSettings().getStudentIdGenerationMethod());
            admissionSettings.setRegisteredStudentIdPrefix(instituteSetting.getAdmissionSettings().getRegisteredStudentIdPrefix());
            if (AdmissionSettings.ID_GENERATION_METHOD_INCREMENTAL.equals(instituteSetting.getAdmissionSettings().getStudentIdGenerationMethod())) {
                admissionSettings.setNextStudentId(instituteSetting.getAdmissionSettings().getNextStudentId());
            } else if (AdmissionSettings.ID_GENERATION_METHOD_CUSTOMIZED.equals(instituteSetting.getAdmissionSettings().getStudentIdGenerationMethod())) {
                admissionSettings.setConfirmStudentIdPrefix(instituteSetting.getAdmissionSettings().getConfirmStudentIdPrefix());
            }
            instituteSetting.setAdmissionSettings(admissionSettings);
        }
        if (instituteSetting.getFeeSettings() != null) {
            FeeSettings feeSettings = persistInstituteSetting.getFeeSettings();
            if (feeSettings == null) {
                feeSettings = new FeeSettings();
            }
            feeSettings.setLastFeeReceiptNo(instituteSetting.getFeeSettings().getLastFeeReceiptNo());
            feeSettings.setFeeReceiptStartNo(instituteSetting.getFeeSettings().getFeeReceiptStartNo());
            feeSettings.setReceiptType(instituteSetting.getFeeSettings().getReceiptType());
            feeSettings.setIsFeeReceiptNoInCont(instituteSetting.getFeeSettings().getIsFeeReceiptNoInCont());
            persistInstituteSetting.setFeeSettings(feeSettings);
        }
        if (instituteSetting.getEnquirySettings() != null) {
            EnquirySettings enquirySettings = persistInstituteSetting.getEnquirySettings();
            if (enquirySettings == null) {
                enquirySettings = new EnquirySettings();
            }
            enquirySettings.setEnableCompetitiveExam(instituteSetting.getEnquirySettings().getEnableCompetitiveExam());
            enquirySettings.setEnableDuplicateEnq(instituteSetting.getEnquirySettings().getEnableDuplicateEnq());
            enquirySettings.setEnableInternalExam(instituteSetting.getEnquirySettings().getEnableInternalExam());
            enquirySettings.setEnablePreviousClass(instituteSetting.getEnquirySettings().getEnablePreviousClass());
            enquirySettings.setEnableRegistered(instituteSetting.getEnquirySettings().getEnableRegistered());
            enquirySettings.setFormFee(instituteSetting.getEnquirySettings().getFormFee());
            enquirySettings.setNextFormReceiptNo(instituteSetting.getEnquirySettings().getNextFormReceiptNo());
            if (instituteSetting.getEnquirySettings().getActiveAcademicYear() != null) {
                enquirySettings.setActiveAcademicYear((AcademicYear) getHibernateTemplate().load(AcademicYear.class, instituteSetting.getEnquirySettings().getActiveAcademicYear().getId()));
            } else {
                enquirySettings.setActiveAcademicYear(null);
            }
            persistInstituteSetting.setEnquirySettings(enquirySettings);
        }
        getHibernateTemplate().update(persistInstituteSetting);
    }
}
