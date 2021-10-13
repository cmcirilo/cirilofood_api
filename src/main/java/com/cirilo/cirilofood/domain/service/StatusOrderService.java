package com.cirilo.cirilofood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cirilo.cirilofood.domain.exception.BusinessException;
import com.cirilo.cirilofood.domain.model.Order;
import com.cirilo.cirilofood.domain.model.StatusOrder;

import java.time.OffsetDateTime;

@Service
public class StatusOrderService {

    @Autowired
    private OrderService orderService;

    @Transactional
    public void confirm(Long orderId) {
        Order order = orderService.find(orderId);

        if (!order.getStatus().equals(StatusOrder.CREATED)) {
            throw new BusinessException(String.format("Status Order %d does not update from %s to %s",
                    order.getId(), order.getStatus().getDescription(), StatusOrder.CONFIRMED.getDescription()));
        }

        order.setStatus(StatusOrder.CONFIRMED);
        order.setConfirmationDate(OffsetDateTime.now());
    }
}
