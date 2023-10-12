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
				new Product("sku-111", "Product 111 Test", 99.99f, new String[] {"foo", "bar"}),
				new Product("sku-222", "Product 222 Test", 99.99f, new String[] {"foo", "baz"})
		));

		final Collection<Product> result = productRepository.findAllByTagsContaining("baz");

		assertThat(result)
				.hasSize(1)
				.extracting(Product::getSku)
				.containsExactly("sku-222");
	}

	@Test
	void findAllByTagsContaining_Specification() {
		productRepository.saveAll(List.of(
				new Product("sku-333", "Product 333 Test", 99.99f, new String[] {"foo", "bar"}),
				new Product("sku-444", "Product 444 Test", 99.99f, new String[] {"foo", "baz"})
		));

		final Collection<Product> result = productRepository.findAllByTagsContaining_Specification("baz");

		assertThat(result)
				.hasSize(1)
				.extracting(Product::getSku)
				.containsExactly("sku-444");
	}

}
