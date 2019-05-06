package com.it.competition.service.impl;

import com.it.competition.dao.OrganizerMapper;
import com.it.competition.domain.Organizer;
import com.it.competition.domain.OrganizerExample;
import com.it.competition.service.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizerServiceImpl implements OrganizerService {

    @Autowired
    private OrganizerMapper organizerMapper;

    @Override
    public Organizer login(Organizer organizer) {
        OrganizerExample example = new OrganizerExample();
        OrganizerExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(organizer.getUserName()).andPasswordEqualTo(organizer.getPassword());
        List<Organizer> organizers = organizerMapper.selectByExample(example);
        return organizers != null && organizers.size() > 0 ? organizers.get(0) : null;
    }

    @Override
    public Organizer getOrganizer(Integer organizerId) {
        return organizerMapper.selectByPrimaryKey(organizerId);
    }
}
