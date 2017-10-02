package com.narendra.sams.academics.dao;

import com.narendra.sams.acad.domain.ClassSectionCount;
import com.narendra.sams.admission.domain.ClassSection;
import java.util.List;

public interface ClassSectionDAO {
    Long addClassSection(ClassSection classSection, Long l);

    List<ClassSection> getClassSections(Long l);

    List<ClassSectionCount> getClasswiseSectionCount(Long l);

    void updateClassSection(ClassSection classSection, Long l);
}
