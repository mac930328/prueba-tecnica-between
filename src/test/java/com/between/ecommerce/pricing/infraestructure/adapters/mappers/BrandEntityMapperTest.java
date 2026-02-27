package com.between.ecommerce.pricing.infraestructure.adapters.mappers;

import com.between.ecommerce.pricing.domain.models.Brand;
import com.between.ecommerce.pricing.infraestructure.adapters.entities.BrandEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class BrandEntityMapperTest {

	private BrandEntityMapper brandEntityMapper;

	@BeforeEach
	void setUp() {
		this.brandEntityMapper = Mappers.getMapper(BrandEntityMapper.class);
	}

	@Test
	void brandToBrandEntityTest() {

		final Brand brand = Brand.builder().id(1L).build();

		final BrandEntity brandEntity = this.brandEntityMapper.brandToBrandEntity(brand);

		assertEquals(brand.getId(), brandEntity.getId());
	}

	@Test
	void brandEntityToBrandTest() {
		final BrandEntity brandEntity = BrandEntity.builder().id(2L).build();

		final Brand brand = this.brandEntityMapper.brandEntityToBrand(brandEntity);

		assertEquals(brandEntity.getId(), brand.getId());
	}

	@Test
	void brandToBrandEntityNullInputReturnsNull() {
		final BrandEntity result = this.brandEntityMapper.brandToBrandEntity(null);
		assertNull(result);
	}

	@Test
	void brandEntityToBrandNullInputReturnsNull() {
		final Brand result = this.brandEntityMapper.brandEntityToBrand(null);
		assertNull(result);
	}
}
