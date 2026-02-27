package com.between.ecommerce.pricing.infraestructure.rest.exceptions;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
@Data
public class GlobalError {

	private Integer status;
	private String message;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime date;

}
