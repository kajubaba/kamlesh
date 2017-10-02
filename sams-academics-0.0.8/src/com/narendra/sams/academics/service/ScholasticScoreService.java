package com.narendra.sams.academics.service;

import com.narendra.sams.academics.exam.domain.ScholasticScore;
import com.narendra.sams.academics.exam.domain.StudentScore;
import java.util.List;

public interface ScholasticScoreService {
    List<StudentScore> getScholasticScore(Long l, Long l2, Long l3, Long l4);

    ScholasticScore getStudnetScholasticScore(Long l, Long l2, Long l3, Long l4);

    void saveScholasticScore(Long l, Long l2, Long l3, List<StudentScore> list);
}
