package com.putatu.service.mapper;

import com.putatu.domain.*;
import com.putatu.service.dto.ProviderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Provider and its DTO ProviderDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProviderMapper extends EntityMapper<ProviderDTO, Provider> {

    

    

    default Provider fromId(Long id) {
        if (id == null) {
            return null;
        }
        Provider provider = new Provider();
        provider.setId(id);
        return provider;
    }
}
