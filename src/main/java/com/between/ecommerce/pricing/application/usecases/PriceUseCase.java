package com.between.ecommerce.pricing.application.usecases;

import com.between.ecommerce.pricing.application.dtos.PriceDto;

import java.time.LocalDateTime;

public interface PriceUseCase {

    PriceDto getPriceByDate(LocalDateTime date, Long productId, Long brandId);
}
