package com.it.competition.service;

import com.it.competition.domain.Message;

import java.util.List;

public interface MessageService {
    int send(Message message);

    List<Message> getUnReadMessages(Integer studentId);

    List<Message> getReadMessages(Integer studentId);

    Message getMessage(Integer id);

    int update(Message message);
}
