package com.cirilo.cirilofood.domain.repository;

import org.springframework.stereotype.Repository;

import com.cirilo.cirilofood.domain.model.User;

@Repository
public interface UserRepository extends CustomJpaRepository<User, Long> {

}
