package tech.aaregall.lab.hibernate6.persistance.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.aaregall.lab.hibernate6.persistance.entity.Product;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepository;

	@Test
	void findAllByTagsContaining() {

		Product product1 = Product.builder()
				.sku("sku-1234")
				.name("1234 Test")
				.price(105.99f)
				.tags(new String[] {"foo", "bar"})
				.build();

		Product product2 = Product.builder()
				.sku("sku-4567")
				.name("4567 Test")
				.price(10.0f)
				.tags(new String[] {"foo", "baz"})
				.build();

		productRepository.saveAll(List.of(product1, product2));

		assertThat(productRepository.findAllByTagsContaining("baz"))
				.asList()
				.hasSize(1)
				.extracting("sku")
				.containsExactly("sku-4567");
	}

}
