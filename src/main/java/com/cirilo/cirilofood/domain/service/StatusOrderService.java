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
    public void confirm(String code) {
        Order order = orderService.find(code);
        order.confirm();
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
