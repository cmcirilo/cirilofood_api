package com.cirilo.cirilofood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.List;
import java.util.stream.Collectors;

import com.cirilo.cirilofood.api.CiriloLinks;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.cirilo.cirilofood.api.controller.OrderController;
import com.cirilo.cirilofood.api.model.OrderModel;
import com.cirilo.cirilofood.domain.model.Order;

@Component
public class OrderModelAssembler extends RepresentationModelAssemblerSupport<Order, OrderModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CiriloLinks ciriloLinks;

    public OrderModelAssembler() {
        super(OrderController.class, OrderModel.class);
    }

    @Override
    public OrderModel toModel(Order order) {
        OrderModel orderModel = createModelWithId(order.getCode(), order);
        modelMapper.map(order, orderModel);

        orderModel.add(ciriloLinks.linkToOrders());

        orderModel.add(ciriloLinks.linkToStatusConfirmationOrder(order.getCode(), "confirmation"));

        orderModel.add(ciriloLinks.linkToStatusDeliveryOrder(order.getCode(), "delivery"));

        orderModel.add(ciriloLinks.linkToStatusCancelattionOrder(order.getCode(), "cancelattion"));

        orderModel.getRestaurant().add(
                ciriloLinks.linkToRestaurant(order.getRestaurant().getId()));

        orderModel.getClient().add(
                ciriloLinks.linkToUser(order.getClient().getId()));

        orderModel.getFormPayment().add(
                ciriloLinks.linkToFormPayment(order.getFormPayment().getId()));

        orderModel.getDeliveryAddress().getCity().add(
                ciriloLinks.linkToCity(order.getDeliveryAddress().getCity().getId()));

        orderModel.getItens().forEach(item -> {
            item.add(ciriloLinks.linkToProduct(
                    orderModel.getRestaurant().getId(), item.getProductId(), "product"));
        });

        return orderModel;
    }

    public List<OrderModel> toCollectionModel(List<Order> orders) {
        return orders.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

}
