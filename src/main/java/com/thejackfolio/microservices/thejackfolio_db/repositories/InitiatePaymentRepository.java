/*
 * Copyright (c) 2024.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.repositories;

import com.thejackfolio.microservices.thejackfolio_db.entities.InitiatePaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InitiatePaymentRepository extends JpaRepository<InitiatePaymentEntity, Integer> {
    Optional<InitiatePaymentEntity> findByMerchantTransactionId(String merchantTransactionId);
}
