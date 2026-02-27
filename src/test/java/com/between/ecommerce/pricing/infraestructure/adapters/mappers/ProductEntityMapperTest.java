package com.between.ecommerce.pricing.infraestructure.adapters.mappers;

import com.between.ecommerce.pricing.domain.models.Product;
import com.between.ecommerce.pricing.infraestructure.adapters.entities.ProductEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class ProductEntityMapperTest {

	private ProductEntityMapper productEntityMapper;

	@BeforeEach
	void setUp() {
		this.productEntityMapper = Mappers.getMapper(ProductEntityMapper.class);
	}

	@Test
	void productEntityToProductTest() {

		final ProductEntity productEntity = ProductEntity.builder().id(1L).build();

		final Product product = this.productEntityMapper.productEntityToProduct(productEntity);

		assertEquals(productEntity.getId(), product.getId());
	}

	@Test
	void productToProductEntityTest() {
		final Product product = Product.builder().id(3L).build();

		final ProductEntity entity = this.productEntityMapper.productToProductEntity(product);

		assertEquals(product.getId(), entity.getId());
	}

	@Test
	void productEntityToProductNullInputReturnsNull() {
		final Product result = this.productEntityMapper.productEntityToProduct(null);
		assertNull(result);
	}

	@Test
	void productToProductEntityNullInputReturnsNull() {
		final ProductEntity result = this.productEntityMapper.productToProductEntity(null);
		assertNull(result);
	}
}
