package com.putatu.service.mapper;

import com.putatu.domain.*;
import com.putatu.service.dto.CustomerTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CustomerType and its DTO CustomerTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CustomerTypeMapper extends EntityMapper<CustomerTypeDTO, CustomerType> {

    

    

    default CustomerType fromId(Long id) {
        if (id == null) {
            return null;
        }
        CustomerType customerType = new CustomerType();
        customerType.setId(id);
        return customerType;
    }
}
