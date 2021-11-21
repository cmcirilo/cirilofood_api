package com.cirilo.cirilofood.infrastructure.service.mail;

import com.cirilo.cirilofood.core.mail.MailProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class SandboxMailService extends SmtpMailService {

    @Autowired
    private MailProperties mailProperties;

    @Override protected MimeMessage createMimeMessage(Message message) throws MessagingException {
        MimeMessage mimeMessage = super.createMimeMessage(message);

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
        mimeMessageHelper.setTo(mailProperties.getSandbox().getRecipient());
        
        return mimeMessage;
    }
}
