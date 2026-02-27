package com.between.ecommerce.pricing.application.mappers;

import com.between.ecommerce.pricing.application.dtos.PriceDto;
import com.between.ecommerce.pricing.domain.models.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class PriceDtoMapperTest {

	private PriceDtoMapper priceDtoMapper;

	@BeforeEach
	void setUp() {
		this.priceDtoMapper = Mappers.getMapper(PriceDtoMapper.class);
	}

	@Test
	void priceDtoToPriceTest() {
		final PriceDto priceDto = PriceDto.builder().price(BigDecimal.valueOf(35.50)).build();

		final Price priceRes = this.priceDtoMapper.priceDtoToPrice(priceDto);

		assertEquals(priceDto.getPrice(), priceRes.getPriceAmount());
	}

	@Test
	void priceToPriceDtoTest() {
		final Price price = Price.builder().priceAmount(BigDecimal.valueOf(25.45)).build();

		final PriceDto dto = this.priceDtoMapper.priceToPriceDto(price);

		assertEquals(price.getPriceAmount(), dto.getPrice());
	}

	@Test
	void nullInputReturnsNullForDtoToPrice() {
		final Price result = this.priceDtoMapper.priceDtoToPrice(null);
		assertNull(result);
	}

	@Test
	void nullInputReturnsNullForPriceToDto() {
		final PriceDto result = this.priceDtoMapper.priceToPriceDto(null);
		assertNull(result);
	}
}
