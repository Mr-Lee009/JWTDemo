package com.mta.jwt.demo.controller;

import com.mta.jwt.demo.entity.acl.NoticeMessage;
import com.mta.jwt.demo.repo.NoticeMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    NoticeMessageRepository noticeMessageRepository;

    @PostMapping(value = "/add-message")
    public NoticeMessage addMessage(NoticeMessage noticeMessage) {
        return noticeMessageRepository.save(noticeMessage);
    }

    @GetMapping(value = "/getById/{id}")
    public NoticeMessage getById(@PathVariable Long id) {
        return noticeMessageRepository.getById(id);
    }

    @GetMapping(value = "/findAll")
    public List<NoticeMessage> findAll() {
        return noticeMessageRepository.findAll();
    }

}
