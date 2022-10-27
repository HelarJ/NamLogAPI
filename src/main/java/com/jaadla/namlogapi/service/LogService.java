package com.jaadla.namlogapi.service;

import com.jaadla.namlogapi.model.Message;
import com.jaadla.namlogapi.repository.MessageRepository;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@CommonsLog
@Service
public class LogService {

  MessageRepository messageRepository;

  public LogService(@Autowired MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  @Cacheable("messages")
  public Page<Message> getMessages(String username, Pageable pageable) {

    return messageRepository.findByUsernameIgnoreCaseOrderByIdAsc(
        username, pageable);
  }

  @Scheduled(cron = "0 0/5 * * * *")
  @CacheEvict(value = "messages", allEntries = true)
  public void evictAllCacheValues() {
  }

}
