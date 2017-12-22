package com.putatu.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.putatu.domain.PaymentCoin;
import com.putatu.service.PaymentCoinService;
import com.putatu.web.rest.errors.BadRequestAlertException;
import com.putatu.web.rest.util.HeaderUtil;
import com.putatu.web.rest.util.PaginationUtil;
import com.putatu.service.dto.PaymentCoinDTO;
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
 * REST controller for managing PaymentCoin.
 */
@RestController
@RequestMapping("/api")
public class PaymentCoinResource {

    private final Logger log = LoggerFactory.getLogger(PaymentCoinResource.class);

    private static final String ENTITY_NAME = "paymentCoin";

    private final PaymentCoinService paymentCoinService;

    public PaymentCoinResource(PaymentCoinService paymentCoinService) {
        this.paymentCoinService = paymentCoinService;
    }

    /**
     * POST  /payment-coins : Create a new paymentCoin.
     *
     * @param paymentCoinDTO the paymentCoinDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new paymentCoinDTO, or with status 400 (Bad Request) if the paymentCoin has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/payment-coins")
    @Timed
    public ResponseEntity<PaymentCoinDTO> createPaymentCoin(@Valid @RequestBody PaymentCoinDTO paymentCoinDTO) throws URISyntaxException {
        log.debug("REST request to save PaymentCoin : {}", paymentCoinDTO);
        if (paymentCoinDTO.getId() != null) {
            throw new BadRequestAlertException("A new paymentCoin cannot already have an ID", ENTITY_NAME, "idexists");
        }
        paymentCoinDTO.setCreateAt(ZonedDateTime.now());
        PaymentCoinDTO result = paymentCoinService.save(paymentCoinDTO);
        return ResponseEntity.created(new URI("/api/payment-coins/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /payment-coins : Updates an existing paymentCoin.
     *
     * @param paymentCoinDTO the paymentCoinDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated paymentCoinDTO,
     * or with status 400 (Bad Request) if the paymentCoinDTO is not valid,
     * or with status 500 (Internal Server Error) if the paymentCoinDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/payment-coins")
    @Timed
    public ResponseEntity<PaymentCoinDTO> updatePaymentCoin(@Valid @RequestBody PaymentCoinDTO paymentCoinDTO) throws URISyntaxException {
        log.debug("REST request to update PaymentCoin : {}", paymentCoinDTO);
        if (paymentCoinDTO.getId() == null) {
            return createPaymentCoin(paymentCoinDTO);
        }
        PaymentCoinDTO result = paymentCoinService.save(paymentCoinDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, paymentCoinDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /payment-coins : get all the paymentCoins.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of paymentCoins in body
     */
    @GetMapping("/payment-coins")
    @Timed
    public ResponseEntity<List<PaymentCoin>> getAllPaymentCoins(Pageable pageable) {
        log.debug("REST request to get a page of PaymentCoins");
        Page<PaymentCoin> page = paymentCoinService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/payment-coins");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /payment-coins/:id : get the "id" paymentCoin.
     *
     * @param id the id of the paymentCoinDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the paymentCoinDTO, or with status 404 (Not Found)
     */
    @GetMapping("/payment-coins/{id}")
    @Timed
    public ResponseEntity<PaymentCoin> getPaymentCoin(@PathVariable Long id) {
        log.debug("REST request to get PaymentCoin : {}", id);
        PaymentCoin paymentCoin = paymentCoinService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(paymentCoin));
    }

    /**
     * DELETE  /payment-coins/:id : delete the "id" paymentCoin.
     *
     * @param id the id of the paymentCoinDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/payment-coins/{id}")
    @Timed
    public ResponseEntity<Void> deletePaymentCoin(@PathVariable Long id) {
        log.debug("REST request to delete PaymentCoin : {}", id);
        paymentCoinService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/payment-coins?query=:query : search for the paymentCoin corresponding
     * to the query.
     *
     * @param query the query of the paymentCoin search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/payment-coins")
    @Timed
    public ResponseEntity<List<PaymentCoinDTO>> searchPaymentCoins(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of PaymentCoins for query {}", query);
        Page<PaymentCoinDTO> page = paymentCoinService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/payment-coins");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
