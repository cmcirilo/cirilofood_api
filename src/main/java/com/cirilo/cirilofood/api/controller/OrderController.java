package com.cirilo.cirilofood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cirilo.cirilofood.api.assembler.OrderInputDisassembler;
import com.cirilo.cirilofood.api.assembler.OrderModelAssembler;
import com.cirilo.cirilofood.api.assembler.OrderResumeModelAssembler;
import com.cirilo.cirilofood.api.model.OrderModel;
import com.cirilo.cirilofood.api.model.OrderResumeModel;
import com.cirilo.cirilofood.api.model.input.OrderInput;
import com.cirilo.cirilofood.domain.exception.BusinessException;
import com.cirilo.cirilofood.domain.exception.EntityNotFoundException;
import com.cirilo.cirilofood.domain.model.Order;
import com.cirilo.cirilofood.domain.model.User;
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

    @Autowired
    private OrderResumeModelAssembler orderResumeModelAssembler;

    @Autowired
    private OrderInputDisassembler orderInputDisassembler;

    @GetMapping
    public List<OrderResumeModel> list() {
        List<Order> allOrders = orderRepository.findAll();

        return orderResumeModelAssembler.toCollectionModel(allOrders);
    }

    @GetMapping("/{code}")
    public OrderModel buscar(@PathVariable String code) {
        Order order = orderService.find(code);

        return orderModelAssembler.toModel(order);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderModel create(@Valid @RequestBody OrderInput orderInput) {
        try {
            Order newOrder = orderInputDisassembler.toDomainObject(orderInput);

            newOrder.setClient(new User());
            newOrder.getClient().setId(1L);

            newOrder = orderService.create(newOrder);

            return orderModelAssembler.toModel(newOrder);
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}
