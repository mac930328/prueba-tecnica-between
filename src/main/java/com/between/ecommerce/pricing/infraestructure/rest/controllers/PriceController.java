package com.between.ecommerce.pricing.infraestructure.rest.controllers;

import com.between.ecommerce.pricing.application.dtos.PriceDto;
import com.between.ecommerce.pricing.application.usecases.PriceUseCase;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping(value = "/api/price", produces = MediaType.APPLICATION_JSON_VALUE)
public class PriceController {

	private final PriceUseCase priceUseCase;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public PriceDto getPriceByDate(@RequestParam("applicationDate") LocalDateTime applicationDate,
			@RequestParam("productId") @Min(value = 1, message = "productId must be greater than 0") Long productId,
			@RequestParam("brandId") @Min(value = 1, message = "brandId must be greater than 0") Long brandId) {
		return this.priceUseCase.getPriceByDate(applicationDate, productId, brandId);
	}
}
