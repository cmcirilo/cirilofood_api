package com.cirilo.cirilofood.domain.event;

import com.cirilo.cirilofood.domain.model.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CanceledOrderEvent {

    private Order order;

}
