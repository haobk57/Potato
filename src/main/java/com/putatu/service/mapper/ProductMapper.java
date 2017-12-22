package com.putatu.service.mapper;

import com.putatu.domain.*;
import com.putatu.service.dto.ProductDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Product and its DTO ProductDTO.
 */
@Mapper(componentModel = "spring", uses = {CategoryMapper.class, ProviderMapper.class})
public interface ProductMapper extends EntityMapper<ProductDTO, Product> {

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "provider.id", target = "providerId")
    ProductDTO toDto(Product product); 

    @Mapping(source = "categoryId", target = "category")
    @Mapping(source = "providerId", target = "provider")
    Product toEntity(ProductDTO productDTO);

    default Product fromId(Long id) {
        if (id == null) {
            return null;
        }
        Product product = new Product();
        product.setId(id);
        return product;
    }
}
