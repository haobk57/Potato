package com.putatu.service.mapper;

import com.putatu.domain.*;
import com.putatu.service.dto.PaymentCoinDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PaymentCoin and its DTO PaymentCoinDTO.
 */
@Mapper(componentModel = "spring", uses = {StaffMapper.class, CustomerMapper.class})
public interface PaymentCoinMapper extends EntityMapper<PaymentCoinDTO, PaymentCoin> {

    @Mapping(source = "staff.id", target = "staffId")
    @Mapping(source = "customer.id", target = "customerId")
    PaymentCoinDTO toDto(PaymentCoin paymentCoin); 

    @Mapping(source = "staffId", target = "staff")
    @Mapping(source = "customerId", target = "customer")
    PaymentCoin toEntity(PaymentCoinDTO paymentCoinDTO);

    default PaymentCoin fromId(Long id) {
        if (id == null) {
            return null;
        }
        PaymentCoin paymentCoin = new PaymentCoin();
        paymentCoin.setId(id);
        return paymentCoin;
    }
}
