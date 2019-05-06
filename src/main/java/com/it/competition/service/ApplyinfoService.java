package com.it.competition.service;

import com.it.competition.domain.Applyinfo;

import java.util.List;

public interface ApplyinfoService {
    List<Applyinfo> getApplyInfosByStudentId(Integer studentId);

    List<Applyinfo> getApplyInfos();

    int setApplyInfoState(Integer id, boolean b);

    int deleteApplyInfo(Integer id);

    Applyinfo getApplyInfo(Integer id);

    List<Applyinfo> getApplyInfosByCompetitionId(Integer competitionId, String teamName);

    List<Applyinfo> getApplyInfosByCompetitionId(Integer competitionId);

    int updateApplyInfos(List<Applyinfo> applyinfos);

    int updateApplyInfo(Applyinfo applyInfo);
}
