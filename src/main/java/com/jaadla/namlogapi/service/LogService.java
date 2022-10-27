package com.jaadla.namlogapi.service;

import com.jaadla.namlogapi.model.Message;
import com.jaadla.namlogapi.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LogService {

  MessageRepository messageRepository;

  public LogService(@Autowired MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  public Page<Message> getMessages(String username, Pageable pageable) {
    Page<Message> messageList = messageRepository.findByUsernameIgnoreCaseOrderByIdAsc(
        username, pageable);

    System.out.println(messageList);

    return messageList;
  }
}
