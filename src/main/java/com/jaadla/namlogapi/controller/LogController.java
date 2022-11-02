package com.jaadla.namlogapi.controller;

import com.jaadla.namlogapi.model.Page;
import com.jaadla.namlogapi.service.LogService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CommonsLog
@RestController
@RequestMapping(path = "/api/log", produces = MediaType.APPLICATION_JSON_VALUE)
public class LogController {

    @Autowired
    LogService logService;

    private final static int MAX_PAGE_SIZE = 2000;

    @GetMapping(value = "/{username}")
    @ResponseBody
    ResponseEntity<Page> getLog(
        @PathVariable String username,
        @RequestParam(defaultValue = "0") int startIndex,
        @RequestParam(defaultValue = "100") int size) {
        if (size > MAX_PAGE_SIZE) {
            size = MAX_PAGE_SIZE;
        }
        if (size < 0) {
            size = 0;
        }

        Page messages = logService.getMessages(username, startIndex, size);
        return ResponseEntity.ok(messages);
    }

    @GetMapping(value = "/")
    @ResponseBody
    String noUsername(HttpServletResponse response) {
        response.setStatus(404);
        return "no username.";
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleInvalidDataAccessApiUsageException(
        InvalidDataAccessApiUsageException exception) {

        log.warn("Invalid Data Access: " + exception.getMessage());
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body("Bad request.");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleIllegalArgumentException(
        IllegalArgumentException exception) {

        log.warn("Invalid Argument: " + exception.getMessage());
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body("Bad request.");
    }
}
