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
 * A PaymentCoin.
 */
@Entity
@Table(name = "payment_coin")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "paymentcoin")
public class PaymentCoin implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "method", nullable = false)
    private String method;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "coin", nullable = false)
    private Float coin;

    @Column(name = "money")
    private Integer money;

    @Column(name = "create_at")
    private ZonedDateTime createAt;

    @ManyToOne
    private Staff staff;

    @ManyToOne
    private Customer customer;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public PaymentCoin method(String method) {
        this.method = method;
        return this;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public PaymentCoin name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getCoin() {
        return coin;
    }

    public PaymentCoin coin(Float coin) {
        this.coin = coin;
        return this;
    }

    public void setCoin(Float coin) {
        this.coin = coin;
    }

    public Integer getMoney() {
        return money;
    }

    public PaymentCoin money(Integer money) {
        this.money = money;
        return this;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public ZonedDateTime getCreateAt() {
        return createAt;
    }

    public PaymentCoin createAt(ZonedDateTime createAt) {
        this.createAt = createAt;
        return this;
    }

    public void setCreateAt(ZonedDateTime createAt) {
        this.createAt = createAt;
    }

    public Staff getStaff() {
        return staff;
    }

    public PaymentCoin staff(Staff staff) {
        this.staff = staff;
        return this;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Customer getCustomer() {
        return customer;
    }

    public PaymentCoin customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
        PaymentCoin paymentCoin = (PaymentCoin) o;
        if (paymentCoin.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), paymentCoin.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PaymentCoin{" +
            "id=" + getId() +
            ", method='" + getMethod() + "'" +
            ", name='" + getName() + "'" +
            ", coin=" + getCoin() +
            ", money=" + getMoney() +
            ", createAt='" + getCreateAt() + "'" +
            "}";
    }
}
