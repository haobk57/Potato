package com.putatu.service.impl;

import com.putatu.service.PaymentCoinService;
import com.putatu.domain.PaymentCoin;
import com.putatu.repository.PaymentCoinRepository;
import com.putatu.repository.search.PaymentCoinSearchRepository;
import com.putatu.service.dto.PaymentCoinDTO;
import com.putatu.service.mapper.PaymentCoinMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing PaymentCoin.
 */
@Service
@Transactional
public class PaymentCoinServiceImpl implements PaymentCoinService{

    private final Logger log = LoggerFactory.getLogger(PaymentCoinServiceImpl.class);

    private final PaymentCoinRepository paymentCoinRepository;

    private final PaymentCoinMapper paymentCoinMapper;

    private final PaymentCoinSearchRepository paymentCoinSearchRepository;

    public PaymentCoinServiceImpl(PaymentCoinRepository paymentCoinRepository, PaymentCoinMapper paymentCoinMapper, PaymentCoinSearchRepository paymentCoinSearchRepository) {
        this.paymentCoinRepository = paymentCoinRepository;
        this.paymentCoinMapper = paymentCoinMapper;
        this.paymentCoinSearchRepository = paymentCoinSearchRepository;
    }

    /**
     * Save a paymentCoin.
     *
     * @param paymentCoinDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PaymentCoinDTO save(PaymentCoinDTO paymentCoinDTO) {
        log.debug("Request to save PaymentCoin : {}", paymentCoinDTO);
        PaymentCoin paymentCoin = paymentCoinMapper.toEntity(paymentCoinDTO);
        paymentCoin = paymentCoinRepository.save(paymentCoin);
        PaymentCoinDTO result = paymentCoinMapper.toDto(paymentCoin);
        paymentCoinSearchRepository.save(paymentCoin);
        return result;
    }

    /**
     * Get all the paymentCoins.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PaymentCoin> findAll(Pageable pageable) {
        log.debug("Request to get all PaymentCoins");
        return paymentCoinRepository.findAll(pageable);
    }

    /**
     * Get one paymentCoin by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public PaymentCoin findOne(Long id) {
        log.debug("Request to get PaymentCoin : {}", id);
        PaymentCoin paymentCoin = paymentCoinRepository.findOne(id);
        return paymentCoin;
    }

    /**
     * Delete the paymentCoin by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PaymentCoin : {}", id);
        paymentCoinRepository.delete(id);
        paymentCoinSearchRepository.delete(id);
    }

    /**
     * Search for the paymentCoin corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PaymentCoinDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of PaymentCoins for query {}", query);
        Page<PaymentCoin> result = paymentCoinSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(paymentCoinMapper::toDto);
    }
}
