package com.cirilo.cirilofood.infrastructure.service.mail;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FakeMailService extends SmtpMailService {

    @Override public void send(Message message) {
        String body = processTemplate(message);

        log.info("[FAKE E-MAIL] Para: {}\n{}", message.getRecipients(), body);
    }

}
