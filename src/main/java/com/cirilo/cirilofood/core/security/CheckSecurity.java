package com.cirilo.cirilofood.core.security;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public @interface CheckSecurity {

    public @interface Cuisines {

        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface AllowList {}

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('UPDATE_CUISINES')")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface AllowUpdate {}

    }

    public @interface Restaurants {

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('UPDATE_RESTAURANTS')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface AllowManageRegistration { }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and " +
                "(hasAuthority('UPDATE_RESTAURANTS') or " +
                "@ciriloSecurity.manageRestaurant(#restaurantId))")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface AllowManageOperation { }

        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface AllowList { }

    }

    public @interface Orders {

        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @PostAuthorize("hasAuthority('LIST_ORDERS') or " +
                "@ciriloSecurity.getUserId() == returnObject.client.id or " +
                "@ciriloSecurity.manageRestaurant(returnObject.restaurant.id)")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface AllowFind { }

        @PreAuthorize("hasAuthority('SCOPE_READ') and (hasAuthority('LIST_ORDERS') or "
                + "@ciriloSecurity.getUserId() == #orderFilter.clientId or"
                + "@ciriloSecurity.manageRestaurant(#orderFilter.restaurantId))")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface AllowSearch { }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and isAuthenticated()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface AllowCreate { }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and (hasAuthority('MANAGE_ORDERS') or "
                + "@ciriloSecurity.manageOrderRestaurant(#code))")
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

        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface AllowList { }

    }

}
