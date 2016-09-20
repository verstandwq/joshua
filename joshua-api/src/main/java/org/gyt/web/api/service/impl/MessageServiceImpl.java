package org.gyt.web.api.service.impl;

import org.gyt.web.api.repository.MessageRepository;
import org.gyt.web.api.service.MessageService;
import org.gyt.web.model.Message;
import org.gyt.web.model.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public List<Message> getAll() {
        return messageRepository.findAll();
    }

    @Override
    public List<Message> getByType(MessageType type) {
        return getAll().stream().filter(message -> message.getType().equals(type)).collect(Collectors.toList());
    }

    @Override
    public boolean create(Message message) {
        return messageRepository.save(message) != null;
    }
}
