package com.jaadla.namlogapi.repository;

import com.jaadla.namlogapi.entity.Message;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MessageRepository extends PagingAndSortingRepository<Message, Integer> {

  @Query(value = "SELECT max(id) from messages", nativeQuery = true)
  int getMaxId();

  @Query(value = "SELECT id, time, username, message from messages where username = :username AND id < :startIndex order by id desc limit :size", nativeQuery = true)
  List<Message> findByUsername(String username, int startIndex, int size);

  @Query(value = "SELECT id, time, username, message from messages where id < :startIndex order by id desc limit :size", nativeQuery = true)
  List<Message> findAllMessages(int startIndex, int size);

  @Query(value = "SELECT count(*) from messages where username = :username", nativeQuery = true)
  int getMessageCountUser(String username);
}