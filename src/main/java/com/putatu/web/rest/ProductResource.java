package com.putatu.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.putatu.domain.Product;
import com.putatu.service.ProductService;
import com.putatu.service.mapper.ProductMapper;
import com.putatu.web.rest.errors.BadRequestAlertException;
import com.putatu.web.rest.util.HeaderUtil;
import com.putatu.web.rest.util.PaginationUtil;
import com.putatu.service.dto.ProductDTO;
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
 * REST controller for managing Product.
 */
@RestController
@RequestMapping("/api")
public class ProductResource {

    private final Logger log = LoggerFactory.getLogger(ProductResource.class);

    private static final String ENTITY_NAME = "product";

    private final ProductService productService;

    private final ProductMapper productMapper;

    public ProductResource(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    /**
     * POST  /products : Create a new product.
     *
     * @param productDTO the productDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new productDTO, or with status 400 (Bad Request) if the product has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/products")
    @Timed
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO) throws URISyntaxException {
        log.debug("REST request to save Product : {}", productDTO);
        if (productDTO.getId() != null) {
            throw new BadRequestAlertException("A new product cannot already have an ID", ENTITY_NAME, "idexists");
        }
        productDTO.setCreateAt(ZonedDateTime.now());
        productDTO.setNumberOfViews(0);
        ProductDTO result = productService.save(productDTO);
        return ResponseEntity.created(new URI("/api/products/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /products : Updates an existing product.
     *
     * @param productDTO the productDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated productDTO,
     * or with status 400 (Bad Request) if the productDTO is not valid,
     * or with status 500 (Internal Server Error) if the productDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/products")
    @Timed
    public ResponseEntity<ProductDTO> updateProduct(@Valid @RequestBody ProductDTO productDTO) throws URISyntaxException {
        log.debug("REST request to update Product : {}", productDTO);
        if (productDTO.getId() == null) {
            return createProduct(productDTO);
        }
        ProductDTO result = productService.save(productDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, productDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /products : get all the products.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of products in body
     */
    @GetMapping("/products")
    @Timed
    public ResponseEntity<List<Product>> getAllProducts(Pageable pageable) {
        log.debug("REST request to get a page of Products");
        Page<Product> page = productService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/products");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /products/:id : get the "id" product.
     *
     * @param id the id of the productDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the productDTO, or with status 404 (Not Found)
     */
    @GetMapping("/products/{id}")
    @Timed
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        log.debug("REST request to get Product : {}", id);
        Product product = productService.findOne(id);

        ProductDTO productDTO = productMapper.toDto(product);
        productDTO.setNumberOfViews(productDTO.getNumberOfViews() + 1);

        log.debug("///////////////////////////////// : {}", product.getNumberOfViews());
        productService.save(productDTO);


        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(product));
    }

    /**
     * DELETE  /products/:id : delete the "id" product.
     *
     * @param id the id of the productDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/products/{id}")
    @Timed
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        log.debug("REST request to delete Product : {}", id);
        productService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/products?query=:query : search for the product corresponding
     * to the query.
     *
     * @param query the query of the product search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/products")
    @Timed
    public ResponseEntity<List<ProductDTO>> searchProducts(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Products for query {}", query);
        Page<ProductDTO> page = productService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/products");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
