package com.cirilo.cirilofood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cirilo.cirilofood.domain.model.FormPayment;

import java.time.OffsetDateTime;

@Repository
public interface FormPaymentRepository extends JpaRepository<FormPayment, Long> {

    @Query("select max(updatedDate) from FormPayment")
    OffsetDateTime getUpdatedDate();

    @Query("select updatedDate from FormPayment where id =:formPaymentId")
    OffsetDateTime getUpdatedDateById(Long formPaymentId);

}
