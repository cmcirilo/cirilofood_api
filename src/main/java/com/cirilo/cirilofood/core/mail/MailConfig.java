package com.cirilo.cirilofood.core.mail;

import com.cirilo.cirilofood.domain.service.MailService;
import com.cirilo.cirilofood.infrastructure.service.mail.FakeMailService;
import com.cirilo.cirilofood.infrastructure.service.mail.SandboxMailService;
import com.cirilo.cirilofood.infrastructure.service.mail.SmtpMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailConfig {

    @Autowired
    private MailProperties mailProperties;

    @Bean
    public MailService mailService(){
        switch (mailProperties.getImpl()){
            case FAKE:
                return new FakeMailService();
            case SMTP:
                return new SmtpMailService();
            case SANDBOX:
                return new SandboxMailService();
            default:
                return null;
        }
    }

}
