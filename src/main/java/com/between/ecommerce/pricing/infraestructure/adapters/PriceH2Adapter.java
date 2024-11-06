package com.between.ecommerce.pricing.infraestructure.adapters;

import com.between.ecommerce.pricing.domain.models.Price;
import com.between.ecommerce.pricing.domain.repositories.PriceRepository;
import com.between.ecommerce.pricing.infraestructure.adapters.entities.BrandEntity;
import com.between.ecommerce.pricing.infraestructure.adapters.entities.ProductEntity;
import com.between.ecommerce.pricing.infraestructure.adapters.mappers.PriceEntityMapper;
import com.between.ecommerce.pricing.infraestructure.adapters.repository.PriceJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PriceH2Adapter implements PriceRepository {

    private final PriceJpaRepository priceJpaRepository;
    private final PriceEntityMapper priceDboMapper;

    @Override
    public Optional<Price> getPriceByDate(LocalDateTime date, Long productId, Long brandId) {

        ProductEntity product = ProductEntity.builder().id(productId).build();
        BrandEntity brand = BrandEntity.builder().id(brandId).build();

        return priceJpaRepository.findFirstPriceByDateAndProductAndBrand(date, product, brand)
                .map(priceDboMapper::priceEntitytoPrice);
    }

}
