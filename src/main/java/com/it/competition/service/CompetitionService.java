package com.it.competition.service;

import com.it.competition.domain.Applyinfo;
import com.it.competition.domain.Competition;

import java.util.List;

public interface CompetitionService {
    List<Competition> getTrueCompetitions();

    List<Competition> getCompetitionsByOrganizerId(Integer id);

    Competition getCompetitionById(Integer id);

    int updateCompetition(Competition competition);

    int createCompetition(Competition competition);

    int delete(Integer id);

    int setCompetitionState(Integer id, boolean state);

    List<Competition> getCompetitions();

    int apply(List<Applyinfo> applyInfos);
}
