package com.jaadla.namlogapi.controller;

import com.jaadla.namlogapi.model.Message;
import com.jaadla.namlogapi.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/api/log", produces = MediaType.APPLICATION_JSON_VALUE)
public class LogController {

    @Autowired
    LogService logService;

    @GetMapping(value = "/{username}")
    @ResponseBody
    Page<Message> getLog(
        @PathVariable String username,
        Pageable pageable) {
        return logService.getMessages(username, pageable);
    }


}
