package com.jaadla.namlogapi.service;

import com.jaadla.namlogapi.entity.Message;
import com.jaadla.namlogapi.entity.Page;
import com.jaadla.namlogapi.repository.MessageRepository;
import java.util.List;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@CommonsLog
@Service
public class LogService {

  MessageRepository messageRepository;

  public LogService(@Autowired MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  public Page getMessages(String username, int startIndex, int size) {
    int totalMessages = -1;
    if (startIndex <= 0) {
      //Since the order is descending it would be impossible to know the last id when the first request is made.
      startIndex = messageRepository.getMaxId() + 10;
      //message count is only included in the first request as it's expensive.
      //for the "all" request it is sufficient to just use the max id for an approximate value.
      totalMessages = username.equalsIgnoreCase("all")
          ? startIndex
          : messageRepository.getMessageCountUser(username);
    }

    List<Message> messages = username.equalsIgnoreCase("all")
        ? messageRepository.findAllMessages(startIndex, size)
        : messageRepository.findByUsername(username, startIndex, size);

    boolean finalPage = messages.size() < size;
    int lastId = messages.size() > 0 ? messages.get(messages.size() - 1).getId() : 0;
    return new Page(messages, lastId, finalPage, totalMessages);
  }
}
