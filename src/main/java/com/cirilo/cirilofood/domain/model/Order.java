package com.cirilo.cirilofood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.cirilo.cirilofood.domain.exception.BusinessException;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "`order`")
public class Order {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal subtotal;

    private BigDecimal shippingFee;

    private BigDecimal totalValue;

    @Embedded
    private Address deliveryAddress;

    @Enumerated(EnumType.STRING)
    private StatusOrder status = StatusOrder.CREATED;

    @CreationTimestamp
    private OffsetDateTime createdDate;

    private OffsetDateTime confirmationDate;

    private OffsetDateTime cancelDate;

    private OffsetDateTime deliveryDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private FormPayment formPayment;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "user_client_id", nullable = false)
    private User client;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> itens = new ArrayList<>();

    public void calculateTotalValue() {
        getItens().forEach(OrderItem::calculateTotalPrice);

        this.subtotal = getItens().stream()
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.totalValue = this.subtotal.add(this.shippingFee);
    }

    public void setShippingFee() {
        setShippingFee(getRestaurant().getShippingFee());
    }

    public void setOrderToItensOrder() {
        getItens().forEach(item -> item.setOrder(this));
    }

    public void confirm() {
        setStatus(StatusOrder.CONFIRMED);
        setConfirmationDate(OffsetDateTime.now());
    }

    public void delivery() {
        setStatus(StatusOrder.DELIVERED);
        setDeliveryDate(OffsetDateTime.now());
    }

    public void cancel() {
        setStatus(StatusOrder.CANCELED);
        setCancelDate(OffsetDateTime.now());
    }

    private void setStatus(StatusOrder newStatus) {
        if (getStatus().shoudNotUpdateTo(newStatus)) {
            throw new BusinessException(
                    String.format("Status do order %d não pode ser alterado de %s para %s",
                            getId(), getStatus().getDescription(),
                            newStatus.getDescription()));
        }

        this.status = newStatus;
    }
}
