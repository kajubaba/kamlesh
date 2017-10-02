package com.narendra.sams.academics.dao;

import com.narendra.sams.academics.exam.domain.ScholasticScore;
import java.util.List;

public interface ScholasticScoreDAO {
    void addStudentScore(Long l, Long l2, Long l3, Long l4, Float f);

    List<ScholasticScore> getScholasticScore(Long l, Long l2, Long l3, Long l4);

    List<ScholasticScore> getStudentScore(Long l, Long l2);

    ScholasticScore getStudnetScholasticScore(Long l, Long l2, Long l3, Long l4);

    void updateStudentScore(Long l, Float f);
}
