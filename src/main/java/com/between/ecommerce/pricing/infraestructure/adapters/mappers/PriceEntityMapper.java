package com.between.ecommerce.pricing.infraestructure.adapters.mappers;

import com.between.ecommerce.pricing.domain.models.Price;
import com.between.ecommerce.pricing.infraestructure.adapters.entities.PriceEntity;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {ProductEntityMapper.class,
        BrandEntityMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PriceEntityMapper {

    @Mapping(target = "priceList", source = "priceList")
    @Mapping(target = "brand", source = "brandEntity")
    @Mapping(target = "product", source = "productEntity")
    @Mapping(target = "startDate", source = "startDate")
    @Mapping(target = "endDate", source = "endDate")
    @Mapping(target = "priority", source = "priority")
    @Mapping(target = "priceAmount", source = "price")
    @Mapping(target = "currency", source = "currency")
    Price priceEntitytoPrice(PriceEntity priceEntity);

    @InheritInverseConfiguration
    PriceEntity priceToPriceEntity(Price price);
}
