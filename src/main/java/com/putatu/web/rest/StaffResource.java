package com.putatu.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.putatu.domain.Staff;
import com.putatu.service.StaffService;
import com.putatu.web.rest.errors.BadRequestAlertException;
import com.putatu.web.rest.util.HeaderUtil;
import com.putatu.web.rest.util.PaginationUtil;
import com.putatu.service.dto.StaffDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Staff.
 */
@RestController
@RequestMapping("/api")
public class StaffResource {

    private final Logger log = LoggerFactory.getLogger(StaffResource.class);

    private static final String ENTITY_NAME = "staff";

    private final StaffService staffService;

    public StaffResource(StaffService staffService) {
        this.staffService = staffService;
    }

    /**
     * POST  /staff : Create a new staff.
     *
     * @param staffDTO the staffDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new staffDTO, or with status 400 (Bad Request) if the staff has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/staff")
    @Timed
    public ResponseEntity<StaffDTO> createStaff(@Valid @RequestBody StaffDTO staffDTO) throws URISyntaxException {
        log.debug("REST request to save Staff : {}", staffDTO);
        if (staffDTO.getId() != null) {
            throw new BadRequestAlertException("A new staff cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StaffDTO result = staffService.save(staffDTO);
        return ResponseEntity.created(new URI("/api/staff/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /staff : Updates an existing staff.
     *
     * @param staffDTO the staffDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated staffDTO,
     * or with status 400 (Bad Request) if the staffDTO is not valid,
     * or with status 500 (Internal Server Error) if the staffDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/staff")
    @Timed
    public ResponseEntity<StaffDTO> updateStaff(@Valid @RequestBody StaffDTO staffDTO) throws URISyntaxException {
        log.debug("REST request to update Staff : {}", staffDTO);
        if (staffDTO.getId() == null) {
            return createStaff(staffDTO);
        }
        StaffDTO result = staffService.save(staffDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, staffDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /staff : get all the staff.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of staff in body
     */
    @GetMapping("/staff")
    @Timed
    public ResponseEntity<List<Staff>> getAllStaff(Pageable pageable) {
        log.debug("REST request to get a page of Staff");
        Page<Staff> page = staffService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/staff");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /staff/:id : get the "id" staff.
     *
     * @param id the id of the staffDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the staffDTO, or with status 404 (Not Found)
     */
    @GetMapping("/staff/{id}")
    @Timed
    public ResponseEntity<Staff> getStaff(@PathVariable Long id) {
        log.debug("REST request to get Staff : {}", id);
        Staff staff = staffService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(staff));
    }

    /**
     * DELETE  /staff/:id : delete the "id" staff.
     *
     * @param id the id of the staffDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/staff/{id}")
    @Timed
    public ResponseEntity<Void> deleteStaff(@PathVariable Long id) {
        log.debug("REST request to delete Staff : {}", id);
        staffService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/staff?query=:query : search for the staff corresponding
     * to the query.
     *
     * @param query the query of the staff search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/staff")
    @Timed
    public ResponseEntity<List<StaffDTO>> searchStaff(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Staff for query {}", query);
        Page<StaffDTO> page = staffService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/staff");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
