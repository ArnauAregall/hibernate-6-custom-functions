package tech.aaregall.lab.hibernate6.persistence.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.aaregall.lab.hibernate6.persistence.entity.Product;

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
				new Product("sku-111", "Dog Food", 30.99f, new String[] {"pets", "home", "dogs", "animals"}),
				new Product("sku-222", "Smart TV", 899.99f, new String[] {"electronics", "home"})
		));

		final Collection<Product> result = productRepository.findAllByTagsContaining("electronics");

		assertThat(result)
				.hasSize(1)
				.extracting(Product::getSku)
				.containsExactly("sku-222");
	}

	@Test
	void findAllByTagsContaining_Specification() {
		productRepository.saveAll(List.of(
				new Product("sku-333", "Running Shoes", 64.55f, new String[] {"sport", "running", "footing"}),
				new Product("sku-444", "Swimsuit", 30.99f, new String[] {"sport", "swimming", "water polo"})
		));

		final Collection<Product> result = productRepository.findAllByTagsContaining_Specification("swimming");

		assertThat(result)
				.hasSize(1)
				.extracting(Product::getSku)
				.containsExactly("sku-444");
	}

}
