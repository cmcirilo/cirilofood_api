package com.cirilo.cirilofood.domain.listener;

import com.cirilo.cirilofood.domain.event.ConfirmedOrderEvent;
import com.cirilo.cirilofood.domain.model.Order;
import com.cirilo.cirilofood.domain.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class NotificationClientConfirmedOrderListener {

    @Autowired
    private MailService mailService;

    //    @EventListener - if not depends transaction

    //    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT) - means that will be execute method before commit,
    //    that is if happens will be result error (Rest Exception)

    @TransactionalEventListener // means that if happens error in send mail the order will be updated anyway.
    public void whenConfirmOrder(ConfirmedOrderEvent event) {

        Order order = event.getOrder();

        var message = MailService.Message.builder()
                .subject(order.getRestaurant().getName() + " - Order confirmed")
                .variable("order", order)
                .body("confirmed-order.html")
                .recipient(order.getClient().getEmail())
                .build();

        mailService.send(message);
    }
}
