package com.putatu.service;

import com.putatu.service.dto.ProviderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Provider.
 */
public interface ProviderService {

    /**
     * Save a provider.
     *
     * @param providerDTO the entity to save
     * @return the persisted entity
     */
    ProviderDTO save(ProviderDTO providerDTO);

    /**
     * Get all the providers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ProviderDTO> findAll(Pageable pageable);

    /**
     * Get the "id" provider.
     *
     * @param id the id of the entity
     * @return the entity
     */
    ProviderDTO findOne(Long id);

    /**
     * Delete the "id" provider.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the provider corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ProviderDTO> search(String query, Pageable pageable);
}
