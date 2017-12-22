package com.putatu.service;

import com.putatu.domain.Staff;
import com.putatu.service.dto.StaffDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Staff.
 */
public interface StaffService {

    /**
     * Save a staff.
     *
     * @param staffDTO the entity to save
     * @return the persisted entity
     */
    StaffDTO save(StaffDTO staffDTO);

    /**
     * Get all the staff.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Staff> findAll(Pageable pageable);

    /**
     * Get the "id" staff.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Staff findOne(Long id);

    /**
     * Delete the "id" staff.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the staff corresponding to the query.
     *
     * @param query the query of the search
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<StaffDTO> search(String query, Pageable pageable);
}
