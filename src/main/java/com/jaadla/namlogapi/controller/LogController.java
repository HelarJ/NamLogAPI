package com.jaadla.namlogapi.controller;

import com.jaadla.namlogapi.model.Message;
import com.jaadla.namlogapi.service.LogService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CommonsLog
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
        Page<Message> messages = logService.getMessages(username, pageable);
        log.info(
            messages.getContent().size() > 0
                ? "Sent logs for: %s. Page %d.".formatted(
                messages.getContent().get(0).getUsername(),
                messages.getPageable().getPageNumber())
                : "No logs found for: " + username);
        return messages;
    }

    @GetMapping(value = "/")
    @ResponseBody
    String noUsername(HttpServletResponse response) {
        response.setStatus(404);
        return "no username.";
    }
}
