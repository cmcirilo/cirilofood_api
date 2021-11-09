package com.cirilo.cirilofood.infrastructure.service.mail;

import com.cirilo.cirilofood.core.mail.MailProperties;
import com.cirilo.cirilofood.domain.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class SmtpMailService implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailProperties mailProperties;

    @Override public void send(Message message) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
            mimeMessageHelper.setFrom(mailProperties.getSender());
            mimeMessageHelper.setTo(message.getRecipients().toArray(new String[0]));
            mimeMessageHelper.setSubject(message.getSubject());
            mimeMessageHelper.setText(message.getBody());

            mailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new MailException("Its not possible send mail", e);
        }

    }
}
