package com.cirilo.cirilofood.core.security.authorizationserver;

import java.util.Collection;

import com.cirilo.cirilofood.domain.model.User;
import org.springframework.security.core.GrantedAuthority;


import lombok.Getter;

@Getter
public class AuthUser extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = 1L;

    private Long userId;
    private String fullName;

    public AuthUser(User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getEmail(), user.getPassword(), authorities);

        this.userId = user.getId();
        this.fullName = user.getName();
    }
}
