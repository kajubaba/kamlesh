package com.narendra.sams.core.service.impl;

import com.narendra.sams.core.dao.InstituteDAO;
import com.narendra.sams.core.domain.Institute;
import com.narendra.sams.core.service.InstituteService;
import java.util.List;

public class InstituteServiceImpl implements InstituteService {
    private InstituteDAO instituteDAO;

    public InstituteDAO getInstituteDAO() {
        return this.instituteDAO;
    }

    public void setInstituteDAO(InstituteDAO instituteDAO) {
        this.instituteDAO = instituteDAO;
    }

    public Institute getDefaultInstitute(Long companyId) {
        return this.instituteDAO.getDefaultInstitute(companyId);
    }

    public Institute getInstitute(Long instituteId) {
        return this.instituteDAO.getInstitute(instituteId);
    }

    public List<Institute> getInstitutes(Long companyId) {
        return this.instituteDAO.getInstitutes(companyId);
    }

    public void updateInstituteDetails(Institute institute) {
        this.instituteDAO.updateInstituteDetails(institute);
    }
}
