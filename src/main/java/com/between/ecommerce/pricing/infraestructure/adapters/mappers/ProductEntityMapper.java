package com.between.ecommerce.pricing.infraestructure.adapters.mappers;

import com.between.ecommerce.pricing.domain.models.Product;
import com.between.ecommerce.pricing.infraestructure.adapters.entities.ProductEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductEntityMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    ProductEntity productToProductEntity(Product product);

    @InheritInverseConfiguration
    Product productEntityToProduct(ProductEntity productEntity);
}
