package com.putatu.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;


/**
 * A Exchange.
 */
@Entity
@Table(name = "exchange")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "exchange")
public class Exchange implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "coin", nullable = false)
    private Float coin;

    @Column(name = "create_at")
    private ZonedDateTime createAt;

    @ManyToOne
    private Customer exchange;

    @ManyToOne
    private Product product;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getCoin() {
        return coin;
    }

    public Exchange coin(Float coin) {
        this.coin = coin;
        return this;
    }

    public void setCoin(Float coin) {
        this.coin = coin;
    }

    public ZonedDateTime getCreateAt() {
        return createAt;
    }

    public Exchange createAt(ZonedDateTime createAt) {
        this.createAt = createAt;
        return this;
    }

    public void setCreateAt(ZonedDateTime createAt) {
        this.createAt = createAt;
    }

    public Customer getExchange() {
        return exchange;
    }

    public Exchange exchange(Customer customer) {
        this.exchange = customer;
        return this;
    }

    public void setExchange(Customer customer) {
        this.exchange = customer;
    }

    public Product getProduct() {
        return product;
    }

    public Exchange product(Product product) {
        this.product = product;
        return this;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Exchange exchange = (Exchange) o;
        if (exchange.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), exchange.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Exchange{" +
            "id=" + getId() +
            ", coin=" + getCoin() +
            ", createAt='" + getCreateAt() + "'" +
            "}";
    }
}
