package com.cirilo.cirilofood.domain.listener;

import com.cirilo.cirilofood.domain.event.ConfirmedOrderEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BonificationClientConfirmedOrderListener {

    @EventListener
    public void whenConfirmOrder(ConfirmedOrderEvent event) {
        System.out.println("Calculating points to client " + event.getOrder().getClient().getName());
    }
    
}
