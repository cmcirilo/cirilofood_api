package com.cirilo.cirilofood.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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

    public boolean isAuthenticated() {
        return getAuthentication().isAuthenticated();
    }

    public boolean hasWriteScope() {
        return hasAuthority("SCOPE_WRITE");
    }

    public boolean hasReadScope() {
        return hasAuthority("SCOPE_READ");
    }

    public boolean allowListRestaurants() {
        return hasReadScope() && isAuthenticated();
    }

    public boolean allowManageRegistrationRestaurant() {
        return hasReadScope() && hasAuthority("UPDATE_RESTAURANTS");
    }

    public boolean allowManageOperationRestaurant(Long restaurantId) {
        return hasReadScope() && (hasAuthority("UPDATE_RESTAURANTS")
                || manageRestaurant(restaurantId));
    }

    public boolean allowListUsersGroupsPermissions() {
        return hasReadScope() && hasAuthority("LIST_USERS_GROUPS_PERMISSIONS");
    }

    public boolean allowUpdateUsersGroupsPermissions() {
        return hasReadScope() && hasAuthority("UPDATE_USERS_GROUPS_PERMISSIONS");
    }

    public boolean allowListOrders(Long clientId, Long restaurantId) {
        return hasReadScope() && (hasAuthority("LIST_ORDERS")
                || userAuthenticatedEquals(clientId) || manageRestaurant(restaurantId));
    }

    public boolean allowListOrders() {
        return isAuthenticated() && hasReadScope();
    }

    public boolean allowListFormsPayment() {
        return isAuthenticated() && hasReadScope();
    }

    public boolean allowListCities() {
        return isAuthenticated() && hasReadScope();
    }

    public boolean allowListStates() {
        return isAuthenticated() && hasReadScope();
    }

    public boolean allowListCuisines() {
        return isAuthenticated() && hasReadScope();
    }

    public boolean allowListStatistics() {
        return hasReadScope() && hasAuthority("GENERATE_REPORTS");
    }
}
