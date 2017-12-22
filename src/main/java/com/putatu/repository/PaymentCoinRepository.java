package com.putatu.repository;

import com.putatu.domain.PaymentCoin;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the PaymentCoin entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaymentCoinRepository extends JpaRepository<PaymentCoin, Long> {

}
