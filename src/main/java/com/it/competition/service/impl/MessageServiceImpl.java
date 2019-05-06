package com.it.competition.service.impl;

import com.it.competition.dao.MessageMapper;
import com.it.competition.domain.Message;
import com.it.competition.domain.MessageExample;
import com.it.competition.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public int send(Message message) {
        return messageMapper.insert(message);
    }

    @Override
    public List<Message> getUnReadMessages(Integer studentId) {
        MessageExample example = new MessageExample();
        MessageExample.Criteria criteria = example.createCriteria();
        criteria.andStudentIdEqualTo(studentId).andStateEqualTo(false);
        return messageMapper.selectByExample(example);
    }

    @Override
    public List<Message> getReadMessages(Integer studentId) {
        MessageExample example = new MessageExample();
        MessageExample.Criteria criteria = example.createCriteria();
        criteria.andStudentIdEqualTo(studentId).andStateEqualTo(true);
        return messageMapper.selectByExample(example);
    }

    @Override
    public Message getMessage(Integer id) {
        return messageMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(Message message) {
        return messageMapper.updateByPrimaryKey(message);
    }
}
