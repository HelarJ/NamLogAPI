package com.jaadla.namlogapi.entity;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class Page implements Serializable {

  List<Message> messages;
  Integer lastId;
  Boolean finalPage;
  Integer totalMessages;
}
