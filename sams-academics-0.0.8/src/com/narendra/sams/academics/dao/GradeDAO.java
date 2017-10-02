package com.narendra.sams.academics.dao;

import com.narendra.sams.academics.domain.GradeScale;
import com.narendra.sams.academics.domain.GradeScalePoint;
import java.util.List;

public interface GradeDAO {
    List<GradeScalePoint> getGradeScalePoints(Long l);

    List<GradeScale> getGradeScales(Long l);
}
