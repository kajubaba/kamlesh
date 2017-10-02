package com.narendra.sams.core.dao;

import com.narendra.sams.core.domain.IDGenerationMethod;

public interface IdGenerationMethodDAO {
    IDGenerationMethod getAcademicSessionClassIDGenerationMethod(Long l);

    void updateNextNo(Long l, Long l2);
}
