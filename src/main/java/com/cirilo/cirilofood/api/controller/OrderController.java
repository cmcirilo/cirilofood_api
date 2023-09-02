package com.cirilo.cirilofood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import com.cirilo.cirilofood.api.openapi.controller.OrderControllerOpenApi;
import com.cirilo.cirilofood.core.data.PageableTranslator;
import com.cirilo.cirilofood.domain.exception.BusinessException;
import com.cirilo.cirilofood.domain.exception.EntityNotFoundException;
import com.cirilo.cirilofood.domain.filter.OrderFilter;
import com.cirilo.cirilofood.domain.model.Order;
import com.cirilo.cirilofood.domain.model.User;
import com.cirilo.cirilofood.domain.repository.OrderRepository;
import com.cirilo.cirilofood.domain.service.OrderService;
import com.cirilo.cirilofood.infrastructure.repository.specification.OrderSpecifications;
import com.google.common.collect.ImmutableMap;

@RestController
@RequestMapping(path = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController implements OrderControllerOpenApi {

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

    // @GetMapping
    // public MappingJacksonValue list(@RequestParam(required = false) String fields) {
    // List<Order> allOrders = orderRepository.findAll();
    // List<OrderResumeModel> ordersModel = orderResumeModelAssembler.toCollectionModel(allOrders);
    //
    // MappingJacksonValue orderWrapper = new MappingJacksonValue(ordersModel);
    //
    // SimpleFilterProvider filterProvider = new SimpleFilterProvider();
    // filterProvider.addFilter("orderFilter", SimpleBeanPropertyFilter.serializeAll());
    //
    // if (StringUtils.isNotBlank(fields)) {
    // filterProvider.addFilter("orderFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields.split(",")));
    // }
    //
    // orderWrapper.setFilters(filterProvider);
    // return orderWrapper;
    // }

    // @GetMapping
    // public List<OrderResumeModel> find(OrderFilter orderFilter) {
    // List<Order> allOrders = orderRepository.findAll(OrderSpecifications.usingFilter(orderFilter));
    //
    // return orderResumeModelAssembler.toCollectionModel(allOrders);
    // }

    @GetMapping
    public Page<OrderResumeModel> find(OrderFilter orderFilter,
            @PageableDefault(size = 10) Pageable pageable) {

        pageable = translatePageable(pageable);

        Page<Order> ordersPage = orderRepository.findAll(
                OrderSpecifications.usingFilter(orderFilter), pageable);

        List<OrderResumeModel> ordersResumeModel = orderResumeModelAssembler
                .toCollectionModel(ordersPage.getContent());

        Page<OrderResumeModel> ordersResumeModelPage = new PageImpl<>(
                ordersResumeModel, pageable, ordersPage.getTotalElements());

        return ordersResumeModelPage;
    }

    @GetMapping("/{code}")
    public OrderModel search(@PathVariable String code) {
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

    private Pageable translatePageable(Pageable pageable) {
        var mapping = ImmutableMap.of(
                "code", "code",
                "restaurant.name", "restaurant.name",
                "clientName", "client.name",
                "totalValue", "totalValue");

        return PageableTranslator.translate(pageable, mapping);
    }
}
