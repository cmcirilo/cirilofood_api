package com.cirilo.cirilofood.domain.listener;

import com.cirilo.cirilofood.domain.event.CanceledOrderEvent;
import com.cirilo.cirilofood.domain.model.Order;
import com.cirilo.cirilofood.domain.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class NotificationClientCanceledListener {

    @Autowired
    private MailService mailService;

    @TransactionalEventListener
    public void whenCancelOrder(CanceledOrderEvent event){

        Order order = event.getOrder();

        var message = MailService.Message.builder()
                .subject(order.getRestaurant().getName() + " - Order canceled")
                .variable("order", order)
                .body("canceled-order.html")
                .recipient(order.getClient().getEmail())
                .build();

        mailService.send(message);

    }
}
