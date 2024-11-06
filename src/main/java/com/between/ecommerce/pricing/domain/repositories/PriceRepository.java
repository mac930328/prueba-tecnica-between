package com.between.ecommerce.pricing.domain.repositories;

import com.between.ecommerce.pricing.domain.models.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository {

    Optional<Price> getPriceByDate(LocalDateTime date, Long productId, Long brandId);
}
