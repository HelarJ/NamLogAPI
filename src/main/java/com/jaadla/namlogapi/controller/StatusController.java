package com.jaadla.namlogapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/status")
public class StatusController {

  @GetMapping("/")
  int getStatus() {
    return 200;
  }
}
