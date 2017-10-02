package com.narendra.sams.academics.dao;

import com.narendra.sams.academics.exam.domain.StudentCoScholasticScore;
import java.util.List;

public interface StudentCoScholasticExamDAO {
    StudentCoScholasticScore getStudentCoScholasticScore(Long l, Long l2, Long l3, Long l4);

    List<StudentCoScholasticScore> getStudentCoScholasticScore(Long l, Long l2);

    List<StudentCoScholasticScore> getStudentCoScholasticScore(Long l, Long l2, Long l3);

    void saveStudentCoScholaticScore(List<StudentCoScholasticScore> list, Long l, Long l2, Long l3);

    void updateStudentCoScholaticScore(List<StudentCoScholasticScore> list, Long l, Long l2, Long l3);
}
