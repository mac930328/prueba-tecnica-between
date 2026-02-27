package com.between.ecommerce.pricing.application.usecases.impl;

import com.between.ecommerce.pricing.application.dtos.PriceDto;
import com.between.ecommerce.pricing.application.mappers.PriceDtoMapper;
import com.between.ecommerce.pricing.domain.exceptions.NotFoundObjectException;
import com.between.ecommerce.pricing.domain.models.Price;
import com.between.ecommerce.pricing.domain.repositories.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PriceUseCaseImplTest {

	@Mock
	private PriceRepository priceRepository;
	@Mock
	private PriceDtoMapper priceDtoMapper;
	@InjectMocks
	private PriceUseCaseImpl priceUseCase;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getPriceByDate_ReturnsPriceDto_WhenPriceExists() {
		final LocalDateTime date = LocalDateTime.now();
		final Long productId = 1L;
		final Long brandId = 1L;
		final Price price = mock(Price.class);
		final PriceDto priceDto = mock(PriceDto.class);

		when(this.priceRepository.getPriceByDate(date, productId, brandId)).thenReturn(Optional.of(price));
		when(this.priceDtoMapper.priceToPriceDto(price)).thenReturn(priceDto);

		final PriceDto result = this.priceUseCase.getPriceByDate(date, productId, brandId);

		assertNotNull(result);
		assertEquals(priceDto, result);
		verify(this.priceRepository).getPriceByDate(date, productId, brandId);
		verify(this.priceDtoMapper).priceToPriceDto(price);
	}

	@Test
	void getPriceByDate_ThrowsNotFoundObjectException_WhenPriceNotFound() {
		final LocalDateTime date = LocalDateTime.now();
		final Long productId = 1L;
		final Long brandId = 1L;

		when(this.priceRepository.getPriceByDate(date, productId, brandId)).thenReturn(Optional.empty());

		final NotFoundObjectException exception = assertThrows(NotFoundObjectException.class,
				() -> this.priceUseCase.getPriceByDate(date, productId, brandId));
		assertEquals(PriceUseCaseImpl.NOT_FOUND_ERROR_MESSAGE, exception.getMessage());
		verify(this.priceRepository).getPriceByDate(date, productId, brandId);
		verifyNoInteractions(this.priceDtoMapper);
	}
}
