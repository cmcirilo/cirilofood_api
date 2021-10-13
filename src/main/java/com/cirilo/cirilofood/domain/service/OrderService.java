package com.cirilo.cirilofood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cirilo.cirilofood.domain.exception.BusinessException;
import com.cirilo.cirilofood.domain.exception.OrderNotFoundException;
import com.cirilo.cirilofood.domain.model.City;
import com.cirilo.cirilofood.domain.model.FormPayment;
import com.cirilo.cirilofood.domain.model.Order;
import com.cirilo.cirilofood.domain.model.Product;
import com.cirilo.cirilofood.domain.model.Restaurant;
import com.cirilo.cirilofood.domain.model.User;
import com.cirilo.cirilofood.domain.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CityService cityService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private FormPaymentService formPaymentService;

    @Autowired
    private OrderRepository orderRepository;

    public Order find(String code) {
        return orderRepository.findByCode(code)
                .orElseThrow(() -> new OrderNotFoundException(code));
    }

    @Transactional
    public Order create(Order order) {
        validateOrder(order);
        validateItens(order);

        order.setShippingFee(order.getRestaurant().getShippingFee());
        order.calculateTotalValue();

        return orderRepository.save(order);
    }

    private void validateOrder(Order order) {
        City city = cityService.find(order.getDeliveryAddress().getCity().getId());
        User client = userService.find(order.getClient().getId());
        Restaurant restaurant = restaurantService.find(order.getRestaurant().getId());
        FormPayment formPayment = formPaymentService.find(order.getFormPayment().getId());

        order.getDeliveryAddress().setCity(city);
        order.setClient(client);
        order.setRestaurant(restaurant);
        order.setFormPayment(formPayment);

        if (restaurant.notAcceptFormPayment(formPayment)) {
            throw new BusinessException(String.format("Form Payment '%s' not accepted in restaurant.",
                    formPayment.getDescription()));
        }
    }

    private void validateItens(Order order) {
        order.getItens().forEach(item -> {
            Product product = productService.find(
                    order.getRestaurant().getId(), item.getProduct().getId());

            item.setOrder(order);
            item.setProduct(product);
            item.setUnitPrice(product.getPrice());
        });
    }
}
