package com.putatu.service;

import com.putatu.domain.PaymentCoin;
import com.putatu.service.dto.PaymentCoinDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing PaymentCoin.
 */
public interface PaymentCoinService {

    /**
     * Save a paymentCoin.
     *
     * @param paymentCoinDTO the entity to save
     * @return the persisted entity
     */
    PaymentCoinDTO save(PaymentCoinDTO paymentCoinDTO);

    /**
     * Get all the paymentCoins.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PaymentCoin> findAll(Pageable pageable);

    /**
     * Get the "id" paymentCoin.
     *
     * @param id the id of the entity
     * @return the entity
     */
    PaymentCoin findOne(Long id);

    /**
     * Delete the "id" paymentCoin.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the paymentCoin corresponding to the query.
     *
     * @param query the query of the search
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PaymentCoinDTO> search(String query, Pageable pageable);
}
