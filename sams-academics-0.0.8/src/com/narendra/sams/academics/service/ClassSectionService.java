package com.narendra.sams.academics.service;

import com.narendra.sams.acad.domain.ClassSectionCount;
import com.narendra.sams.admission.domain.ClassSection;
import java.util.List;

public interface ClassSectionService {
    List<ClassSection> getClassSections(Long l);

    List<ClassSectionCount> getClasswiseSectionCount(Long l);

    void saveClassSection(ClassSection classSection, Long l);
}
