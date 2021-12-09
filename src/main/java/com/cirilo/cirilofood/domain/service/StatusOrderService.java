package com.cirilo.cirilofood.domain.service;

import com.cirilo.cirilofood.domain.model.Order;
import com.cirilo.cirilofood.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StatusOrderService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public void confirm(String code) {
        Order order = orderService.find(code);
        order.confirm();

        //to force register event ConfirmedOrderEvent in method confirm
        orderRepository.save(order);

        //        var message = Message.builder()
        //                .subject(order.getRestaurant().getName() + " - Order confirmed")
        //                .variable("order",order)
        //                .body("confirmed-order.html")
        //                .recipient(order.getClient().getEmail())
        //                .build();
        //
        //        mailService.send(message);
    }

    @Transactional
    public void cancel(String code) {
        Order order = orderService.find(code);
        order.cancel();
    }

    @Transactional
    public void delivery(String code) {
        Order order = orderService.find(code);
        order.delivery();
    }
}
