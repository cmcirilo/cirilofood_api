package com.cirilo.cirilofood.infrastructure.service.mail;

import com.cirilo.cirilofood.core.mail.MailProperties;
import com.cirilo.cirilofood.domain.service.MailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;

public class SmtpMailService implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailProperties mailProperties;

    @Autowired
    private Configuration freeMarkerConfiguration;

    @Override public void send(Message message) {
        try {
            String body = processTemplate(message);

            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
            mimeMessageHelper.setFrom(mailProperties.getSender());
            mimeMessageHelper.setTo(message.getRecipients().toArray(new String[0]));
            mimeMessageHelper.setSubject(message.getSubject());
            mimeMessageHelper.setText(body, true);

            mailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new MailException("Its not possible send mail", e);
        }

    }

    protected String processTemplate(Message message) {
        try {
            Template template = freeMarkerConfiguration.getTemplate(message.getBody());

            return FreeMarkerTemplateUtils.processTemplateIntoString(template, message.getVariables());
        } catch (Exception e) {
            throw new MailException("Its not possible process template mail", e);
        }

    }
}

