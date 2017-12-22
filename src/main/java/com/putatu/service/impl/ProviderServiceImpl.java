package com.putatu.service.impl;

import com.putatu.service.ProviderService;
import com.putatu.domain.Provider;
import com.putatu.repository.ProviderRepository;
import com.putatu.repository.search.ProviderSearchRepository;
import com.putatu.service.dto.ProviderDTO;
import com.putatu.service.mapper.ProviderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Provider.
 */
@Service
@Transactional
public class ProviderServiceImpl implements ProviderService{

    private final Logger log = LoggerFactory.getLogger(ProviderServiceImpl.class);

    private final ProviderRepository providerRepository;

    private final ProviderMapper providerMapper;

    private final ProviderSearchRepository providerSearchRepository;

    public ProviderServiceImpl(ProviderRepository providerRepository, ProviderMapper providerMapper, ProviderSearchRepository providerSearchRepository) {
        this.providerRepository = providerRepository;
        this.providerMapper = providerMapper;
        this.providerSearchRepository = providerSearchRepository;
    }

    /**
     * Save a provider.
     *
     * @param providerDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProviderDTO save(ProviderDTO providerDTO) {
        log.debug("Request to save Provider : {}", providerDTO);
        Provider provider = providerMapper.toEntity(providerDTO);
        provider = providerRepository.save(provider);
        ProviderDTO result = providerMapper.toDto(provider);
        providerSearchRepository.save(provider);
        return result;
    }

    /**
     * Get all the providers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProviderDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Providers");
        return providerRepository.findAll(pageable)
            .map(providerMapper::toDto);
    }

    /**
     * Get one provider by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ProviderDTO findOne(Long id) {
        log.debug("Request to get Provider : {}", id);
        Provider provider = providerRepository.findOne(id);
        return providerMapper.toDto(provider);
    }

    /**
     * Delete the provider by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Provider : {}", id);
        providerRepository.delete(id);
        providerSearchRepository.delete(id);
    }

    /**
     * Search for the provider corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProviderDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Providers for query {}", query);
        Page<Provider> result = providerSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(providerMapper::toDto);
    }
}
