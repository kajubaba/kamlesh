package com.narendra.sams.core.service;

import com.narendra.sams.core.domain.IDGenerationMethod;

public interface IdGenerationMethodService {
    IDGenerationMethod getAcademicSessionClassIDGenerationMethod(Long l);

    void updateNextNo(Long l, Long l2);
}
