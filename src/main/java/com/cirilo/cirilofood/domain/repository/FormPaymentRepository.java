package com.cirilo.cirilofood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cirilo.cirilofood.domain.model.FormPayment;

@Repository
public interface FormPaymentRepository extends JpaRepository<FormPayment, Long> {

}
