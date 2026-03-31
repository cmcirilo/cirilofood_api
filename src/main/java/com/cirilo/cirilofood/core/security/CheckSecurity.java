package com.cirilo.cirilofood.core.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

public @interface CheckSecurity {

    public @interface Cuisines {

        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @Retention(RUNTIME)
        @Target(ElementType.METHOD)
        @interface AllowList {}

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('UPDATE_CUISINES')")
        @Retention(RUNTIME)
        @Target(ElementType.METHOD)
        @interface AllowUpdate {}

    }

    public @interface Restaurants {

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('UPDATE_RESTAURANTS')")
        @Retention(RUNTIME)
        @Target(ElementType.METHOD)
        public @interface AllowManageRegistration { }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and " +
                "(hasAuthority('UPDATE_RESTAURANTS') or " +
                "@ciriloSecurity.manageRestaurant(#restaurantId))")
        @Retention(RUNTIME)
        @Target(ElementType.METHOD)
        public @interface AllowManageOperation { }

        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @Retention(RUNTIME)
        @Target(ElementType.METHOD)
        public @interface AllowList { }

    }

}
