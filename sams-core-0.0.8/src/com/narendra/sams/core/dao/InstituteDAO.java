package com.narendra.sams.core.dao;

import com.narendra.sams.core.domain.Institute;
import java.util.List;

public interface InstituteDAO {
    Institute getDefaultInstitute(Long l);

    Institute getInstitute(Long l);

    List<Institute> getInstitutes(Long l);

    void updateInstituteDetails(Institute institute);
}
