package com.putatu.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.putatu.domain.Customer;
import com.putatu.domain.Exchange;
import com.putatu.service.CustomerService;
import com.putatu.service.ExchangeService;
import com.putatu.service.mapper.CustomerMapper;
import com.putatu.web.rest.errors.BadRequestAlertException;
import com.putatu.web.rest.util.HeaderUtil;
import com.putatu.web.rest.util.PaginationUtil;
import com.putatu.service.dto.ExchangeDTO;
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

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Exchange.
 */
@RestController
@RequestMapping("/api")
public class ExchangeResource {

    private final Logger log = LoggerFactory.getLogger(ExchangeResource.class);

    private static final String ENTITY_NAME = "exchange";

    private final ExchangeService exchangeService;
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    public ExchangeResource(ExchangeService exchangeService, CustomerService customerService, CustomerMapper customerMapper) {
        this.exchangeService = exchangeService;
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    /**
     * POST  /exchanges : Create a new exchange.
     *
     * @param exchangeDTO the exchangeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new exchangeDTO, or with status 400 (Bad Request) if the exchange has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/exchanges")
    @Timed
    public ResponseEntity<ExchangeDTO> createExchange(@Valid @RequestBody ExchangeDTO exchangeDTO) throws URISyntaxException {
        log.debug("REST request to save Exchange : {}", exchangeDTO);
        if (exchangeDTO.getId() != null) {
            throw new BadRequestAlertException("A new exchange cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ExchangeDTO result = exchangeService.save(exchangeDTO);

        Customer customer = customerService.findOne(exchangeDTO.getExchangeId());
        customer.setCoin(customer.getCoin()+ exchangeDTO.getCoin());
        customerService.save(customerMapper.toDto(customer));

        return ResponseEntity.created(new URI("/api/exchanges/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /exchanges : Updates an existing exchange.
     *
     * @param exchangeDTO the exchangeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated exchangeDTO,
     * or with status 400 (Bad Request) if the exchangeDTO is not valid,
     * or with status 500 (Internal Server Error) if the exchangeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/exchanges")
    @Timed
    public ResponseEntity<ExchangeDTO> updateExchange(@Valid @RequestBody ExchangeDTO exchangeDTO) throws URISyntaxException {
        log.debug("REST request to update Exchange : {}", exchangeDTO);
        if (exchangeDTO.getId() == null) {
            return createExchange(exchangeDTO);
        }
        exchangeDTO.setCreateAt(ZonedDateTime.now());
        ExchangeDTO result = exchangeService.save(exchangeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, exchangeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /exchanges : get all the exchanges.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of exchanges in body
     */
    @GetMapping("/exchanges")
    @Timed
    public ResponseEntity<List<Exchange>> getAllExchanges(Pageable pageable) {
        log.debug("REST request to get a page of Exchanges");
        Page<Exchange> page = exchangeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/exchanges");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /exchanges/:id : get the "id" exchange.
     *
     * @param id the id of the exchangeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the exchangeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/exchanges/{id}")
    @Timed
    public ResponseEntity<Exchange> getExchange(@PathVariable Long id) {
        log.debug("REST request to get Exchange : {}", id);
        Exchange exchange = exchangeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(exchange));
    }

    /**
     * DELETE  /exchanges/:id : delete the "id" exchange.
     *
     * @param id the id of the exchangeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/exchanges/{id}")
    @Timed
    public ResponseEntity<Void> deleteExchange(@PathVariable Long id) {
        log.debug("REST request to delete Exchange : {}", id);
        exchangeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/exchanges?query=:query : search for the exchange corresponding
     * to the query.
     *
     * @param query the query of the exchange search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/exchanges")
    @Timed
    public ResponseEntity<List<ExchangeDTO>> searchExchanges(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Exchanges for query {}", query);
        Page<ExchangeDTO> page = exchangeService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/exchanges");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
