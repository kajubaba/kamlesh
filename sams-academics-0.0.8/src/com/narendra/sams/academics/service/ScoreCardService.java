package com.narendra.sams.academics.service;

import com.narendra.sams.academics.domain.ScoreCard;

public interface ScoreCardService {
    ScoreCard getBlankScholasticScoreCard(Long l);

    ScoreCard getBlankScoreCard(Long l);

    ScoreCard getStudentScholasticScoreCard(Long l, Long l2);

    ScoreCard getStudentScholasticScoreCardOfOneTerm(Long l, Long l2, Long l3);

    ScoreCard getStudentScoreCard(Long l, Long l2);
}
