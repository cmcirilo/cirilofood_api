package com.cirilo.cirilofood.core.security;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public @interface CheckSecurity {

    public @interface Cuisines {

        @PreAuthorize("@ciriloSecurity.allowListCuisines()")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface AllowList {}

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('UPDATE_CUISINES')")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface AllowUpdate {}

    }

    public @interface Restaurants {

        @PreAuthorize("@ciriloSecurity.allowManageRegistrationRestaurant()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface AllowManageRegistration { }

        @PreAuthorize("@ciriloSecurity.allowManageOperationRestaurant(#restaurantId)")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface AllowManageOperation { }

        @PreAuthorize("@ciriloSecurity.allowListRestaurants()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface AllowList { }

    }

    public @interface Orders {

        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @PostAuthorize("hasAuthority('LIST_ORDERS') or " +
                "@ciriloSecurity.userAuthenticatedEquals(returnObject.client.id) or " +
                "@ciriloSecurity.manageRestaurant(returnObject.restaurant.id)")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface AllowFind { }

        @PreAuthorize("@ciriloSecurity.allowListOrders(#orderFilter.clientId, #orderFilter.restaurantId)")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface AllowSearch { }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and isAuthenticated()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface AllowCreate { }

        @PreAuthorize("@ciriloSecurity.allowManageOrders(#code)")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface AllowManageOrder {
        }

    }

    public @interface FormsPayments {

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('UPDATE_FORMS_PAYMENT')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface AllowUpdate { }

        @PreAuthorize("@ciriloSecurity.allowListFormsPayment()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface AllowList { }

    }

    public @interface Cities {

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('UPDATE_CITIES')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface AllowUpdate { }

        @PreAuthorize("@ciriloSecurity.allowListCities()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface AllowList { }

    }

    public @interface States {

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('UPDATE_STATES')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface AllowUpdate { }

        @PreAuthorize("@ciriloSecurity.allowListStates()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface AllowList { }

    }

    public @interface UsersGroupsPermissions {

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and "
                + "@ciriloSecurity.userAuthenticatedEquals(#userId)")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface AllowUpdatePassword { }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and (hasAuthority('UPDATE_USERS_GROUPS_PERMISSIONS') or "
                + "@ciriloSecurity.userAuthenticatedEquals(#userId))")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface AllowUpdateUser { }

        @PreAuthorize("@ciriloSecurity.allowUpdateUsersGroupsPermissions()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface AllowUpdate { }


        @PreAuthorize("@ciriloSecurity.allowListUsersGroupsPermissions()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface AllowList { }

    }

    public @interface Statistics {

        @PreAuthorize("@ciriloSecurity.allowListStatistics()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface AllowList { }

    }

}
