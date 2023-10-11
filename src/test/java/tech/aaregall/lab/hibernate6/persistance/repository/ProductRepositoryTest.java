package tech.aaregall.lab.hibernate6.persistance.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.aaregall.lab.hibernate6.persistance.entity.Product;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepository;

	@BeforeEach
	void beforeEach() {
		productRepository.deleteAll();
	}

	@Test
	void findAllByTagsContaining() {
		productRepository.saveAll(List.of(
				Product.builder()
						.sku("sku-111")
						.name("Product 111 Test")
						.price(105.99f)
						.tags(new String[] {"foo", "bar"})
						.build(),
				Product.builder()
						.sku("sku-222")
						.name("Product 222 Test")
						.price(10.0f)
						.tags(new String[] {"foo", "baz"})
						.build()));

		final Collection<Product> result = productRepository.findAllByTagsContaining("baz");

		assertThat(result)
				.hasSize(1)
				.extracting(Product::getSku)
				.containsExactly("sku-222");
	}

	@Test
	void findAllByTagsContaining_Specification() {
		productRepository.saveAll(List.of(
				Product.builder()
						.sku("sku-333")
						.name("Product 333 Test")
						.price(99f)
						.tags(new String[] {"foo", "bar"})
						.build(),
				Product.builder()
						.sku("sku-444")
						.name("Product 444 Test")
						.price(1000f)
						.tags(new String[] {"foo", "baz"})
						.build()));

		final Collection<Product> result = productRepository.findAllByTagsContaining_Specification("baz");

		assertThat(result)
				.hasSize(1)
				.extracting(Product::getSku)
				.containsExactly("sku-444");
	}

}
