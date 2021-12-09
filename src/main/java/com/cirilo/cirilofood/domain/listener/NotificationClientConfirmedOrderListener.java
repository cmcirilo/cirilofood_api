package com.cirilo.cirilofood.domain.listener;

import com.cirilo.cirilofood.domain.event.ConfirmedOrderEvent;
import com.cirilo.cirilofood.domain.model.Order;
import com.cirilo.cirilofood.domain.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationClientConfirmedOrderListener {

    @Autowired
    private MailService mailService;

    @EventListener
    public void whenConfirmOrder(ConfirmedOrderEvent event){

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
