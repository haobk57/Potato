package com.putatu.service.mapper;

import com.putatu.domain.*;
import com.putatu.service.dto.CustomerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Customer and its DTO CustomerDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, CustomerTypeMapper.class})
public interface CustomerMapper extends EntityMapper<CustomerDTO, Customer> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "type.id", target = "typeId")
    CustomerDTO toDto(Customer customer); 

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "typeId", target = "type")
    Customer toEntity(CustomerDTO customerDTO);

    default Customer fromId(Long id) {
        if (id == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setId(id);
        return customer;
    }
}
