package com.between.ecommerce.pricing.application.dtos;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class PriceDto {

	private Long priceList;
	private Long productId;
	private Long brandId;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private BigDecimal price;

}
