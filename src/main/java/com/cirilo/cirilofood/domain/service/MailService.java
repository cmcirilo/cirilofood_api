package com.cirilo.cirilofood.domain.service;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

public interface MailService {

    void send(Message message);

    @Getter
    @Builder
    class Message {

        private Set<String> recipients;

        private String subject;

        private String body;

    }

}
