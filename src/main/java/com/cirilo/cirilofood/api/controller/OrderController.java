package com.cirilo.cirilofood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cirilo.cirilofood.api.assembler.OrderModelAssembler;
import com.cirilo.cirilofood.api.model.OrderModel;
import com.cirilo.cirilofood.domain.model.Order;
import com.cirilo.cirilofood.domain.repository.OrderRepository;
import com.cirilo.cirilofood.domain.service.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderModelAssembler orderModelAssembler;

    @GetMapping
    public List<OrderModel> list() {
        List<Order> allOrders = orderRepository.findAll();

        return orderModelAssembler.toCollectionModel(allOrders);
    }

    @GetMapping("/{orderId}")
    public OrderModel buscar(@PathVariable Long orderId) {
        Order order = orderService.find(orderId);

        return orderModelAssembler.toModel(order);
    }
}
