package com.cirilo.cirilofood.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import com.cirilo.cirilofood.domain.repository.OrderRepository;
import com.cirilo.cirilofood.domain.repository.RestaurantRepository;

@Component
public class CiriloSecurity {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private OrderRepository orderRepository;

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Long getUserId() {
        Jwt jwt = (Jwt) getAuthentication().getPrincipal();

        return jwt.getClaim("user_id");
    }

    public boolean manageRestaurant(Long restaurantId) {
        if (restaurantId == null) {
            return false;
        }

        return restaurantRepository.existsByOwner(restaurantId, getUserId());
    }

    public boolean manageOrderRestaurant(String code) {
        return orderRepository.isOrderManagedBy(code, getUserId());
    }

    public boolean userAuthenticatedEquals(Long userId) {
        return getUserId() != null && userId != null
                && getUserId().equals(userId);
    }

    public boolean hasAuthority(String authorityName) {
        return getAuthentication().getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(authorityName));
    }

    public boolean allowManageOrders(String code) {
        return hasAuthority("SCOPE_WRITE") && (hasAuthority("MANAGE_ORDERS")
                || manageOrderRestaurant(code));
    }
}
