package com.putatu.service.mapper;

import com.putatu.domain.*;
import com.putatu.service.dto.ExchangeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Exchange and its DTO ExchangeDTO.
 */
@Mapper(componentModel = "spring", uses = {CustomerMapper.class, ProductMapper.class})
public interface ExchangeMapper extends EntityMapper<ExchangeDTO, Exchange> {

    @Mapping(source = "exchange.id", target = "exchangeId")
    @Mapping(source = "product.id", target = "productId")
    ExchangeDTO toDto(Exchange exchange); 

    @Mapping(source = "exchangeId", target = "exchange")
    @Mapping(source = "productId", target = "product")
    Exchange toEntity(ExchangeDTO exchangeDTO);

    default Exchange fromId(Long id) {
        if (id == null) {
            return null;
        }
        Exchange exchange = new Exchange();
        exchange.setId(id);
        return exchange;
    }
}
