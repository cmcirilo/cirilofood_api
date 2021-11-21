package com.cirilo.cirilofood.core.mail;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
@Getter
@Setter
@Component
@ConfigurationProperties("cirilofood.mail")
public class MailProperties {

    @NotNull
    private String sender;

    private Sandbox sandbox = new Sandbox();

    private Implementation impl = Implementation.FAKE;

    public enum Implementation {
        SMTP, FAKE, SANDBOX;
    }

    @Getter
    @Setter
    public class Sandbox {

        private String recipient;

    }
    
}


