package com.it.competition.service.impl;

import com.it.competition.dao.ApplyinfoMapper;
import com.it.competition.domain.Applyinfo;
import com.it.competition.domain.ApplyinfoExample;
import com.it.competition.service.ApplyinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ApplyinfoServiceImpl implements ApplyinfoService {

    @Autowired
    private ApplyinfoMapper applyinfoMapper;

    @Override
    public List<Applyinfo> getApplyInfosByStudentId(Integer studentId) {
        ApplyinfoExample example = new ApplyinfoExample();
        ApplyinfoExample.Criteria criteria = example.createCriteria();
        criteria.andStudentIdEqualTo(studentId);
        return applyinfoMapper.selectByExample(example);
    }

    @Override
    public List<Applyinfo> getApplyInfos() {
        return applyinfoMapper.selectByExample(new ApplyinfoExample());
    }

    @Override
    public int setApplyInfoState(Integer id, boolean b) {
        Applyinfo applyinfo = applyinfoMapper.selectByPrimaryKey(id);
        applyinfo.setState(b);
        return applyinfoMapper.updateByPrimaryKey(applyinfo);
    }

    @Override
    public int deleteApplyInfo(Integer id) {
        return applyinfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Applyinfo getApplyInfo(Integer id) {
        return applyinfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Applyinfo> getApplyInfosByCompetitionId(Integer competitionId, String teamName) {
        ApplyinfoExample example = new ApplyinfoExample();
        ApplyinfoExample.Criteria criteria = example.createCriteria();
        criteria.andCompetitionIdEqualTo(competitionId);
        if (teamName != null) {
            criteria.andTeamNameEqualTo(teamName);
        }
        return applyinfoMapper.selectByExample(example);
    }

    @Override
    public List<Applyinfo> getApplyInfosByCompetitionId(Integer competitionId) {
        ApplyinfoExample example = new ApplyinfoExample();
        ApplyinfoExample.Criteria criteria = example.createCriteria();
        criteria.andCompetitionIdEqualTo(competitionId);
        return applyinfoMapper.selectByExample(example);
    }

    @Override
    public int updateApplyInfos(List<Applyinfo> applyinfos) {
        int i = 0;
        for (Applyinfo applyinfo : applyinfos) {
            applyinfoMapper.updateByPrimaryKey(applyinfo);
            i++;
        }
        return i;
    }

    @Override
    public int updateApplyInfo(Applyinfo applyInfo) {
        return applyinfoMapper.updateByPrimaryKey(applyInfo);
    }
}
