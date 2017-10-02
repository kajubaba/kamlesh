package com.narendra.sams.academics.service;

import com.narendra.sams.academics.exam.domain.StudentCoScholasticAssessmentStatus;
import com.narendra.sams.academics.exam.domain.StudentCoScholasticScore;
import com.narendra.sams.academics.exam.domain.coscholastic.Activity;
import java.util.List;

public interface StudentCoScholasticExamService {
    List<StudentCoScholasticAssessmentStatus> getStduentsWithStatus(Long l, Long l2, Long l3);

    List<Activity> getStudentCoScholasticScore(Long l, Long l2, Long l3);

    List<Activity> getStudentCoScholasticScoreNew(Long l, Long l2, Long l3);

    void saveStudentCoScholaticScore(List<StudentCoScholasticScore> list, Long l, Long l2, Long l3);

    void updateStudentCoScholaticScore(List<StudentCoScholasticScore> list, Long l, Long l2, Long l3);
}
