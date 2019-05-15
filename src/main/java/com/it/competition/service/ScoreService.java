package com.it.competition.service;

import com.it.competition.domain.Score;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ScoreService {
    List<Score> getAllScores(Integer competitionId);

    int addScore(Score score);

    List<Score> batchImport(Integer id, MultipartFile result);

    Score getScoreByCompetitionIdAndStudentId(Integer valueOf, Integer id);
}
