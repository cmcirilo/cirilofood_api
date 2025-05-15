package com.cirilo.cirilofood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import com.cirilo.cirilofood.api.CiriloLinks;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.cirilo.cirilofood.api.controller.CityController;
import com.cirilo.cirilofood.api.controller.FormPaymentController;
import com.cirilo.cirilofood.api.controller.OrderController;
import com.cirilo.cirilofood.api.controller.RestaurantController;
import com.cirilo.cirilofood.api.controller.RestaurantProductController;
import com.cirilo.cirilofood.api.controller.UserController;
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
        OrderModel orderModel = createModelWithId(order.getId(), order);
        modelMapper.map(order, orderModel);

        orderModel.add(ciriloLinks.linkToOrders());

        orderModel.getRestaurant().add(linkTo(methodOn(RestaurantController.class)
                .find(order.getRestaurant().getId())).withSelfRel());

        orderModel.getClient().add(linkTo(methodOn(UserController.class)
                .find(order.getClient().getId())).withSelfRel());

        // Passamos null no segundo argumento, porque é indiferente para a
        // construção da URL do recurso de forma de pagamento
        orderModel.getFormPayment().add(linkTo(methodOn(FormPaymentController.class)
                .find(order.getFormPayment().getId(), null)).withSelfRel());

        orderModel.getDeliveryAddress().getCity().add(linkTo(methodOn(CityController.class)
                .find(order.getDeliveryAddress().getCity().getId())).withSelfRel());

        orderModel.getItens().forEach(item -> {
            item.add(linkTo(methodOn(RestaurantProductController.class)
                    .find(orderModel.getRestaurant().getId(), item.getProductId()))
                    .withRel("product"));
        });

        return orderModel;
    }

    public List<OrderModel> toCollectionModel(List<Order> orders) {
        return orders.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

}
