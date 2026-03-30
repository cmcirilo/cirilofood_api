package com.cirilo.cirilofood.core.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckSecurity {

    public @interface Cuisines {

        @PreAuthorize("isAuthenticated()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface AllowList {}

        @PreAuthorize("hasAuthority('UPDATE_CUISINES')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface AllowUpdate {}

    }

}
