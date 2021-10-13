package com.cirilo.cirilofood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cirilo.cirilofood.domain.model.Order;

@Service
public class StatusOrderService {

    @Autowired
    private OrderService orderService;

    @Transactional
    public void confirm(Long orderId) {
        Order order = orderService.find(orderId);
        order.confirm();
    }

    @Transactional
    public void cancel(Long orderId) {
        Order order = orderService.find(orderId);
        order.cancel();
    }

    @Transactional
    public void delivery(Long orderId) {
        Order order = orderService.find(orderId);
        order.delivery();
    }
}
