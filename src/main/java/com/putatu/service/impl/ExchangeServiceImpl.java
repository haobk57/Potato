package com.putatu.service.impl;

import com.putatu.service.ExchangeService;
import com.putatu.domain.Exchange;
import com.putatu.repository.ExchangeRepository;
import com.putatu.repository.search.ExchangeSearchRepository;
import com.putatu.service.dto.ExchangeDTO;
import com.putatu.service.mapper.ExchangeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Exchange.
 */
@Service
@Transactional
public class ExchangeServiceImpl implements ExchangeService{

    private final Logger log = LoggerFactory.getLogger(ExchangeServiceImpl.class);

    private final ExchangeRepository exchangeRepository;

    private final ExchangeMapper exchangeMapper;

    private final ExchangeSearchRepository exchangeSearchRepository;

    public ExchangeServiceImpl(ExchangeRepository exchangeRepository, ExchangeMapper exchangeMapper, ExchangeSearchRepository exchangeSearchRepository) {
        this.exchangeRepository = exchangeRepository;
        this.exchangeMapper = exchangeMapper;
        this.exchangeSearchRepository = exchangeSearchRepository;
    }

    /**
     * Save a exchange.
     *
     * @param exchangeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ExchangeDTO save(ExchangeDTO exchangeDTO) {
        log.debug("Request to save Exchange : {}", exchangeDTO);
        Exchange exchange = exchangeMapper.toEntity(exchangeDTO);
        exchange = exchangeRepository.save(exchange);
        ExchangeDTO result = exchangeMapper.toDto(exchange);
        exchangeSearchRepository.save(exchange);
        return result;
    }

    /**
     * Get all the exchanges.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Exchange> findAll(Pageable pageable) {
        log.debug("Request to get all Exchanges");
        return exchangeRepository.findAll(pageable);
    }

    /**
     * Get one exchange by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Exchange findOne(Long id) {
        log.debug("Request to get Exchange : {}", id);
        Exchange exchange = exchangeRepository.findOne(id);
        return exchange;
    }

    /**
     * Delete the exchange by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Exchange : {}", id);
        exchangeRepository.delete(id);
        exchangeSearchRepository.delete(id);
    }

    /**
     * Search for the exchange corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ExchangeDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Exchanges for query {}", query);
        Page<Exchange> result = exchangeSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(exchangeMapper::toDto);
    }
}
