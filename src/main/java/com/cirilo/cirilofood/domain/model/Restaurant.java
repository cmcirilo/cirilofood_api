package com.cirilo.cirilofood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.cirilo.cirilofood.core.validation.ZeroValueIncludeDescription;

import lombok.Data;
import lombok.EqualsAndHashCode;

// if shipping fee is zero check if description field contains the mandatory description
@ZeroValueIncludeDescription(valueField = "shippingFee", descriptionField = "name", descriptionMandatory = "Free Tax Delivery")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurant {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @NotNull
    // @NotEmpty
    // @NotBlank - its not necessary because uses in Representation Domail Model - RestaurantInput
    @Column(nullable = false)
    private String name;

    // @DecimalMin("1")
    // @PositiveOrZero(message = "{ShippingFee.invalida}")
    // @PositiveOrZero
    // @NotNull
    // @ShippingFee custom bean validation
    // @Multiple(number = 5) custom contraint validator
    @Column(name = "shipping_fee", nullable = false)
    private BigDecimal shippingFee;

    // @Valid // validate properties inside cuisine with annotations using Bean Validation
    // @NotNull // validate null in cuisine but not properties inside cuisine (not cascade)
    // @ConvertGroup(from = Default.class, to = Groups.CuisineId.class)
    @ManyToOne // (fetch = FetchType.LAZY)
    @JoinColumn(name = "cuisine_id", nullable = false)
    private Cuisine cuisine;

    @Embedded
    private Address address;

    @CreationTimestamp
    @Column(name = "created_date", nullable = false, columnDefinition = "datetime")
    private OffsetDateTime createdDate;

    @UpdateTimestamp
    @Column(name = "updated_date", nullable = false, columnDefinition = "datetime")
    private OffsetDateTime updatedDate;

    private Boolean active = Boolean.TRUE;

    private Boolean opened = Boolean.FALSE;

    @ManyToMany // (fetch = FetchType.EAGER)
    @JoinTable(name = "restaurant_form_payment",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "form_payment_id"))
    private Set<FormPayment> formsPayment = new HashSet<>();

    @OneToMany(mappedBy = "restaurant")
    private List<Product> products = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "restaurant_owner",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> owners = new HashSet<>();

    public void activate() {
        setActive(true);
    }

    public void desactivate() {
        setActive(false);
    }

    public void open() {
        setOpened(true);
    }

    public void close() {
        setOpened(false);
    }

    public boolean removeFormPayment(FormPayment formPayment) {
        return getFormsPayment().remove(formPayment);
    }

    public boolean addFormPayment(FormPayment formPayment) {
        return getFormsPayment().add(formPayment);
    }

    public boolean removeOwner(User user) {
        return getOwners().remove(user);
    }

    public boolean addOwner(User user) {
        return getOwners().add(user);
    }

    public boolean acceptFormPayment(FormPayment formPayment) {
        return getFormsPayment().contains(formPayment);
    }

    public boolean notAcceptFormPayment(FormPayment formPayment) {
        return !acceptFormPayment(formPayment);
    }

    public boolean isOpen() {
        return this.opened;
    }

    public boolean isClosed() {
        return !isOpen();
    }

    public boolean isDesactivate() {
        return !isActivate();
    }

    public boolean isActivate() {
        return this.active;
    }

    public boolean openAllowed() {
        return isActivate() && isClosed();
    }

    public boolean activateAllowed() {
        return isDesactivate();
    }

    public boolean desactivateAllowed() {
        return isActivate();
    }

    public boolean closeAllowed() {
        return isOpen();
    }
}
