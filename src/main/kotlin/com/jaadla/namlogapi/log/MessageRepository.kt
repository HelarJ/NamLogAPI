package com.jaadla.namlogapi.log

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository

interface MessageRepository : PagingAndSortingRepository<MessageEntity, Int> {
    @Query(value = "SELECT max(id) from messages", nativeQuery = true)
    fun maxId(): Int

    @Query(value = "SELECT id, time, username, message from messages where username = :username AND id < :startIndex order by id desc limit :size", nativeQuery = true)
    fun findByUsername(username: String, startIndex: Int, size: Int): List<MessageEntity>

    @Query(value = "SELECT id, time, username, message from messages where id < :startIndex order by id desc limit :size", nativeQuery = true)
    fun findAllMessages(startIndex: Int, size: Int): List<MessageEntity>

    @Query(value = "SELECT count(*) from messages where username = :username", nativeQuery = true)
    fun getMessageCountUser(username: String): Int
}