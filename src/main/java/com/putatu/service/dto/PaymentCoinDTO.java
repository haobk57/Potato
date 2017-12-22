package com.putatu.service.dto;


import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the PaymentCoin entity.
 */
public class PaymentCoinDTO implements Serializable {

    private Long id;

    @NotNull
    private String method;

    @NotNull
    private String name;

    @NotNull
    private Float coin;

    private Integer money;

    private ZonedDateTime createAt;

    private Long staffId;

    private Long customerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getCoin() {
        return coin;
    }

    public void setCoin(Float coin) {
        this.coin = coin;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public ZonedDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(ZonedDateTime createAt) {
        this.createAt = createAt;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PaymentCoinDTO paymentCoinDTO = (PaymentCoinDTO) o;
        if(paymentCoinDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), paymentCoinDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PaymentCoinDTO{" +
            "id=" + getId() +
            ", method='" + getMethod() + "'" +
            ", name='" + getName() + "'" +
            ", coin=" + getCoin() +
            ", money=" + getMoney() +
            ", createAt='" + getCreateAt() + "'" +
            "}";
    }
}
