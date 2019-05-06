package com.it.competition.service.impl;

import com.it.competition.dao.ApplyinfoMapper;
import com.it.competition.dao.CompetitionMapper;
import com.it.competition.domain.Applyinfo;
import com.it.competition.domain.Competition;
import com.it.competition.domain.CompetitionExample;
import com.it.competition.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetitionServiceImpl implements CompetitionService {

    @Autowired
    private CompetitionMapper competitionMapper;
    @Autowired
    private ApplyinfoMapper applyinfoMapper;

    @Override
    public List<Competition> getTrueCompetitions() {
        CompetitionExample example = new CompetitionExample();
        CompetitionExample.Criteria criteria = example.createCriteria();
        criteria.andStateEqualTo(true);
        return competitionMapper.selectByExample(example);
    }

    @Override
    public List<Competition> getCompetitionsByOrganizerId(Integer id) {
        CompetitionExample example = new CompetitionExample();
        CompetitionExample.Criteria criteria = example.createCriteria();
        criteria.andOrganizerIdEqualTo(id);
        return competitionMapper.selectByExample(example);
    }

    @Override
    public Competition getCompetitionById(Integer id) {
        return competitionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateCompetition(Competition competition) {
        return competitionMapper.updateByPrimaryKey(competition);
    }

    @Override
    public int createCompetition(Competition competition) {
        return competitionMapper.insert(competition);
    }

    @Override
    public int delete(Integer id) {
        return competitionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int setCompetitionState(Integer id, boolean state) {
        Competition competition = competitionMapper.selectByPrimaryKey(id);
        competition.setState(state);
        return competitionMapper.updateByPrimaryKey(competition);
    }

    @Override
    public List<Competition> getCompetitions() {
        return competitionMapper.selectByExample(new CompetitionExample());
    }

    @Override
    public int apply(List<Applyinfo> applyInfos) {
        int result = 0;
        for (Applyinfo applyinfo : applyInfos) {
            applyinfoMapper.insert(applyinfo);
            result++;
        }
        return result;
    }
}
