package com.putatu.service.impl;

import com.putatu.service.CustomerTypeService;
import com.putatu.domain.CustomerType;
import com.putatu.repository.CustomerTypeRepository;
import com.putatu.repository.search.CustomerTypeSearchRepository;
import com.putatu.service.dto.CustomerTypeDTO;
import com.putatu.service.mapper.CustomerTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing CustomerType.
 */
@Service
@Transactional
public class CustomerTypeServiceImpl implements CustomerTypeService{

    private final Logger log = LoggerFactory.getLogger(CustomerTypeServiceImpl.class);

    private final CustomerTypeRepository customerTypeRepository;

    private final CustomerTypeMapper customerTypeMapper;

    private final CustomerTypeSearchRepository customerTypeSearchRepository;

    public CustomerTypeServiceImpl(CustomerTypeRepository customerTypeRepository, CustomerTypeMapper customerTypeMapper, CustomerTypeSearchRepository customerTypeSearchRepository) {
        this.customerTypeRepository = customerTypeRepository;
        this.customerTypeMapper = customerTypeMapper;
        this.customerTypeSearchRepository = customerTypeSearchRepository;
    }

    /**
     * Save a customerType.
     *
     * @param customerTypeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CustomerTypeDTO save(CustomerTypeDTO customerTypeDTO) {
        log.debug("Request to save CustomerType : {}", customerTypeDTO);
        CustomerType customerType = customerTypeMapper.toEntity(customerTypeDTO);
        customerType = customerTypeRepository.save(customerType);
        CustomerTypeDTO result = customerTypeMapper.toDto(customerType);
        customerTypeSearchRepository.save(customerType);
        return result;
    }

    /**
     * Get all the customerTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CustomerTypes");
        return customerTypeRepository.findAll(pageable)
            .map(customerTypeMapper::toDto);
    }

    /**
     * Get one customerType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CustomerTypeDTO findOne(Long id) {
        log.debug("Request to get CustomerType : {}", id);
        CustomerType customerType = customerTypeRepository.findOne(id);
        return customerTypeMapper.toDto(customerType);
    }

    /**
     * Delete the customerType by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CustomerType : {}", id);
        customerTypeRepository.delete(id);
        customerTypeSearchRepository.delete(id);
    }

    /**
     * Search for the customerType corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerTypeDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of CustomerTypes for query {}", query);
        Page<CustomerType> result = customerTypeSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(customerTypeMapper::toDto);
    }
}
