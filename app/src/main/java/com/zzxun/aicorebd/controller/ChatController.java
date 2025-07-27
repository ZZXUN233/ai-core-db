package com.zzxun.aicorebd.controller;

import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatClient chatClient;

    @GetMapping
    public String chat(@RequestParam String message) {
        var response = chatClient.call(List.of(new Message(Role.USER, message)));
        return response.getResult().getOutput().getContent();
    }
}
