package com.jaadla.namlogapi.repository;

import com.jaadla.namlogapi.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends PagingAndSortingRepository<Message, Integer> {

  @Query("select m from Message m where m.username = ?1 order by m.id desc")
  Page<Message> findByUsernameIgnoreCaseOrderByIdAsc(String username, Pageable pageable);

}