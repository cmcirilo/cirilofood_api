package com.cirilo.cirilofood.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.cirilo.cirilofood.api.controller.PermissionController;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.stereotype.Component;

import com.cirilo.cirilofood.api.controller.CityController;
import com.cirilo.cirilofood.api.controller.CuisineController;
import com.cirilo.cirilofood.api.controller.FormPaymentController;
import com.cirilo.cirilofood.api.controller.GroupController;
import com.cirilo.cirilofood.api.controller.GroupPermissionController;
import com.cirilo.cirilofood.api.controller.OrderController;
import com.cirilo.cirilofood.api.controller.RestaurantController;
import com.cirilo.cirilofood.api.controller.RestaurantFormPaymentController;
import com.cirilo.cirilofood.api.controller.RestaurantOwnerController;
import com.cirilo.cirilofood.api.controller.RestaurantProductController;
import com.cirilo.cirilofood.api.controller.RestaurantProductPhotoController;
import com.cirilo.cirilofood.api.controller.StateController;
import com.cirilo.cirilofood.api.controller.StatusOrderController;
import com.cirilo.cirilofood.api.controller.UserController;
import com.cirilo.cirilofood.api.controller.UserGroupController;

@Component
public class CiriloLinks {

    public static final TemplateVariables PAGINATION_VARIABLES = new TemplateVariables(
            new TemplateVariable("page", TemplateVariable.VariableType.REQUEST_PARAM),
            new TemplateVariable("size", TemplateVariable.VariableType.REQUEST_PARAM),
            new TemplateVariable("sort", TemplateVariable.VariableType.REQUEST_PARAM));

    public static final TemplateVariables PROJECTION_VARIABLES = new TemplateVariables(
            new TemplateVariable("projection", TemplateVariable.VariableType.REQUEST_PARAM));

    public Link linkToOrders(String rel) {

        TemplateVariables filterVariables = new TemplateVariables(
                new TemplateVariable("clientId", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("restaurantId", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("initialCreatedDate", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("finalCreatedDate", TemplateVariable.VariableType.REQUEST_PARAM));

        String ordersUrl = linkTo(OrderController.class).toUri().toString();

        return new Link(UriTemplate.of(ordersUrl, PAGINATION_VARIABLES.concat(filterVariables)), rel);
        // orderModel.add(linkTo(OrderController.class).withRel("orders"));
    }

    public Link linkToStatusConfirmationOrder(String orderId, String rel) {
        return linkTo(methodOn(StatusOrderController.class)
                .confirm(orderId)).withRel(rel);
    }

    public Link linkToStatusDeliveryOrder(String orderId, String rel) {
        return linkTo(methodOn(StatusOrderController.class)
                .delivery(orderId)).withRel(rel);
    }

    public Link linkToStatusCancelattionOrder(String orderId, String rel) {
        return linkTo(methodOn(StatusOrderController.class)
                .cancel(orderId)).withRel(rel);
    }

    public Link linkToRestaurant(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantController.class)
                .find(restaurantId)).withRel(rel);
    }

    public Link linkToRestaurant(Long restaurantId) {
        return linkToRestaurant(restaurantId, IanaLinkRelations.SELF.value());
    }

    public Link linkToUser(Long userId, String rel) {
        return linkTo(methodOn(UserController.class)
                .find(userId)).withRel(rel);
    }

    public Link linkToUser(Long userId) {
        return linkToUser(userId, IanaLinkRelations.SELF.value());
    }

    public Link linkToUsers(String rel) {
        return linkTo(UserController.class).withRel(rel);
    }

    public Link linkToUsers() {
        return linkToUsers(IanaLinkRelations.SELF.value());
    }

    public Link linkToGroupsUser(Long userId, String rel) {
        return linkTo(methodOn(UserGroupController.class)
                .list(userId)).withRel(rel);
    }

    public Link linkToGroupsUser(Long userId) {
        return linkToGroupsUser(userId, IanaLinkRelations.SELF.value());
    }

    public Link linkToOwnersRestaurant(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantOwnerController.class)
                .list(restaurantId)).withRel(rel);
    }

    public Link linkToOwnersRestaurant(Long restaurantId) {
        return linkToOwnersRestaurant(restaurantId, IanaLinkRelations.SELF.value());
    }

    public Link linkToFormPayment(Long formPaymentId, String rel) {
        return linkTo(methodOn(FormPaymentController.class)
                .find(formPaymentId, null)).withRel(rel);
    }

    public Link linkToFormPayment(Long formPaymentId) {
        return linkToFormPayment(formPaymentId, IanaLinkRelations.SELF.value());
    }

    public Link linkToCity(Long cityId, String rel) {
        return linkTo(methodOn(CityController.class)
                .find(cityId)).withRel(rel);
    }

    public Link linkToCity(Long cityId) {
        return linkToCity(cityId, IanaLinkRelations.SELF.value());
    }

    public Link linkToCities(String rel) {
        return linkTo(CityController.class).withRel(rel);
    }

    public Link linkToCities() {
        return linkToCities(IanaLinkRelations.SELF.value());
    }

    public Link linkToState(Long stateId, String rel) {
        return linkTo(methodOn(StateController.class)
                .find(stateId)).withRel(rel);
    }

    public Link linkToState(Long stateId) {
        return linkToState(stateId, IanaLinkRelations.SELF.value());
    }

    public Link linkToStates(String rel) {
        return linkTo(StateController.class).withRel(rel);
    }

    public Link linkToStates() {
        return linkToStates(IanaLinkRelations.SELF.value());
    }

    public Link linkToProduct(Long restaurantId, Long productId, String rel) {
        return linkTo(methodOn(RestaurantProductController.class)
                .find(restaurantId, productId))
                .withRel(rel);
    }

    public Link linkToProduct(Long restaurantId, Long productId) {
        return linkToProduct(restaurantId, productId, IanaLinkRelations.SELF.value());
    }

    public Link linkToCuisines(String rel) {
        return linkTo(CuisineController.class).withRel(rel);
    }

    public Link linkToCuisines() {
        return linkToCuisines(IanaLinkRelations.SELF.value());
    }

    public Link linkToRestaurants(String rel) {
        String restaurantsUrl = linkTo(RestaurantController.class).toUri().toString();
        return new Link(UriTemplate.of(restaurantsUrl, PROJECTION_VARIABLES), rel);
    }

    public Link linkToRestaurants() {
        return linkToRestaurants(IanaLinkRelations.SELF.value());
    }

    public Link linkToRestaurantFormsPayment(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantFormPaymentController.class)
                .list(restaurantId)).withRel(rel);
    }

    public Link linkToCuisine(Long cuisineId, String rel) {
        return linkTo(methodOn(CuisineController.class)
                .find(cuisineId)).withRel(rel);
    }

    public Link linkToCuisine(Long cuisineId) {
        return linkToCuisine(cuisineId, IanaLinkRelations.SELF.value());
    }

    public Link linkToRestaurantOpen(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantController.class)
                .open(restaurantId)).withRel(rel);
    }

    public Link linkToRestaurantClose(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantController.class)
                .close(restaurantId)).withRel(rel);
    }

    public Link linkToRestaurantDesactivate(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantController.class)
                .desactivate(restaurantId)).withRel(rel);
    }

    public Link linkToRestaurantActivate(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantController.class)
                .activate(restaurantId)).withRel(rel);
    }

    public Link linkToRestaurantFormsPayment(Long restaurantId) {
        return linkToRestaurantFormsPayment(restaurantId, IanaLinkRelations.SELF.value());
    }

    public Link linkToRestaurantFormPaymentDisassociate(Long restaurantId, Long formPaymentId, String rel) {
        return linkTo(methodOn(RestaurantFormPaymentController.class)
                .disassociateFormPayment(restaurantId, formPaymentId)).withRel(rel);
    }

    public Link linkToRestaurantFormPaymentAssociate(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantFormPaymentController.class)
                .associateFormPayment(restaurantId, null)).withRel(rel);
    }

    public Link linkToFormsPayment(String rel) {
        return linkTo(FormPaymentController.class).withRel(rel);
    }

    public Link linkToFormsPayment() {
        return linkToFormsPayment(IanaLinkRelations.SELF.value());
    }

    public Link linkToRestaurantOwnerDisassociate(
            Long restaurantId, Long userId, String rel) {

        return linkTo(methodOn(RestaurantOwnerController.class)
                .disassociate(restaurantId, userId)).withRel(rel);
    }

    public Link linkToRestaurantOwnerAssociate(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantOwnerController.class)
                .associate(restaurantId, null)).withRel(rel);
    }

    public Link linkToProducts(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantProductController.class)
                .list(restaurantId, null)).withRel(rel);
    }

    public Link linkToProducts(Long restaurantId) {
        return linkToProducts(restaurantId, IanaLinkRelations.SELF.value());
    }

    public Link linkToProductPhoto(Long restaurantId, Long productId, String rel) {
        return linkTo(methodOn(RestaurantProductPhotoController.class)
                .find(restaurantId, productId)).withRel(rel);
    }

    public Link linkToProductPhoto(Long restauranteId, Long produtoId) {
        return linkToProductPhoto(restauranteId, produtoId, IanaLinkRelations.SELF.value());
    }

    public Link linkToGroups(String rel) {
        return linkTo(GroupController.class).withRel(rel);
    }

    public Link linkToGroups() {
        return linkToGroups(IanaLinkRelations.SELF.value());
    }

    public Link linkToGroupPermissions(Long groupId, String rel) {
        return linkTo(methodOn(GroupPermissionController.class)
                .list(groupId)).withRel(rel);
    }

    public Link linkToPermissions(String rel) {
        return linkTo(PermissionController.class).withRel(rel);
    }

    public Link linkToPermissions() {
        return linkToPermissions(IanaLinkRelations.SELF.value());
    }

    public Link linkToGroupPermissions(Long groupId) {
        return linkToGroupPermissions(groupId, IanaLinkRelations.SELF.value());
    }

    public Link linkToGroupPermissionsAssociation(Long groupId, String rel) {
        return linkTo(methodOn(GroupPermissionController.class)
                .associate(groupId, null)).withRel(rel);
    }

    public Link linkToGroupPermissionsDesassociation(Long groupId, Long permissionId, String rel) {
        return linkTo(methodOn(GroupPermissionController.class)
                .desassociate(groupId, permissionId)).withRel(rel);
    }

    public Link linkToUserGroupAssociation(Long userId, String rel) {
        return linkTo(methodOn(UserGroupController.class)
                .associate(userId, null)).withRel(rel);
    }

    public Link linkToUserGroupDesassociation(Long userId, Long groupId, String rel) {
        return linkTo(methodOn(UserGroupController.class)
                .disassociate(userId, groupId)).withRel(rel);
    }
}
