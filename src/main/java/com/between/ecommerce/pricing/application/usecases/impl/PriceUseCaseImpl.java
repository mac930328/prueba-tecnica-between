package com.between.ecommerce.pricing.application.usecases.impl;

import com.between.ecommerce.pricing.application.dtos.PriceDto;
import com.between.ecommerce.pricing.application.mappers.PriceDtoMapper;
import com.between.ecommerce.pricing.application.usecases.PriceUseCase;
import com.between.ecommerce.pricing.domain.exceptions.NotFoundObjectException;
import com.between.ecommerce.pricing.domain.repositories.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class PriceUseCaseImpl implements PriceUseCase {

    public static final String NOT_FOUND_ERROR_MESSAGE = "There is no record";

    private final PriceRepository priceRepository;
    private final PriceDtoMapper priceDtoMapper;

    @Override
    public PriceDto getPriceByDate(LocalDateTime date, Long productId, Long brandId) {
        return priceRepository.getPriceByDate(date, productId, brandId)
                .map(priceDtoMapper::priceToPriceDto)
                .orElseThrow(() -> new NotFoundObjectException(NOT_FOUND_ERROR_MESSAGE));
    }

}
