package com.cirilo.cirilofood.api.v1.assembler;

import java.util.List;
import java.util.stream.Collectors;

import com.cirilo.cirilofood.api.v1.CiriloLinks;
import com.cirilo.cirilofood.api.v1.controller.OrderController;
import com.cirilo.cirilofood.api.v1.controller.RestaurantController;
import com.cirilo.cirilofood.api.v1.controller.UserController;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.cirilo.cirilofood.api.v1.model.OrderResumeModel;
import com.cirilo.cirilofood.domain.model.Order;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderResumeModelAssembler extends RepresentationModelAssemblerSupport<Order, OrderResumeModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CiriloLinks ciriloLinks;


    public OrderResumeModelAssembler() {
        super(OrderController.class, OrderResumeModel.class);
    }

    @Override
    public OrderResumeModel toModel(Order order) {
        OrderResumeModel orderModel = createModelWithId(order.getId(), order);
        modelMapper.map(order, orderModel);

        orderModel.add(ciriloLinks.linkToOrders("orders"));

        orderModel.getRestaurant().add(linkTo(methodOn(RestaurantController.class)
                .find(order.getRestaurant().getId())).withSelfRel());

        orderModel.getClient().add(linkTo(methodOn(UserController.class)
                .find(order.getClient().getId())).withSelfRel());

        return orderModel;
    }

    public List<OrderResumeModel> toCollectionModel(List<Order> orders) {
        return orders.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

}
