package com.putatu.service.dto;


import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Exchange entity.
 */
public class ExchangeDTO implements Serializable {

    private Long id;

    @NotNull
    private Float coin;

    private ZonedDateTime createAt;

    private Long exchangeId;

    private Long productId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getCoin() {
        return coin;
    }

    public void setCoin(Float coin) {
        this.coin = coin;
    }

    public ZonedDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(ZonedDateTime createAt) {
        this.createAt = createAt;
    }

    public Long getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(Long customerId) {
        this.exchangeId = customerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ExchangeDTO exchangeDTO = (ExchangeDTO) o;
        if(exchangeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), exchangeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ExchangeDTO{" +
            "id=" + getId() +
            ", coin=" + getCoin() +
            ", createAt='" + getCreateAt() + "'" +
            "}";
    }
}
