package com.putatu.service;

import com.putatu.domain.Exchange;
import com.putatu.service.dto.ExchangeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Exchange.
 */
public interface ExchangeService {

    /**
     * Save a exchange.
     *
     * @param exchangeDTO the entity to save
     * @return the persisted entity
     */
    ExchangeDTO save(ExchangeDTO exchangeDTO);

    /**
     * Get all the exchanges.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Exchange> findAll(Pageable pageable);

    /**
     * Get the "id" exchange.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Exchange findOne(Long id);

    /**
     * Delete the "id" exchange.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the exchange corresponding to the query.
     *
     * @param query the query of the search
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ExchangeDTO> search(String query, Pageable pageable);
}
