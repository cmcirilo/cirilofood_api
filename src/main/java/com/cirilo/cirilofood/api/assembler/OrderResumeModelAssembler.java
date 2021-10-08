package com.cirilo.cirilofood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cirilo.cirilofood.api.model.OrderResumeModel;
import com.cirilo.cirilofood.domain.model.Order;

@Component
public class OrderResumeModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public OrderResumeModel toModel(Order order) {
        return modelMapper.map(order, OrderResumeModel.class);
    }

    public List<OrderResumeModel> toCollectionModel(List<Order> orders) {
        return orders.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

}
