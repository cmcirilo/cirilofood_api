package com.cirilo.cirilofood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cirilo.cirilofood.domain.model.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}
