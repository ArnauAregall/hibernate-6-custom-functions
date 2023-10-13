package tech.aaregall.lab.hibernate6.persistance.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import tech.aaregall.lab.hibernate6.persistance.entity.Product;

import java.util.Collection;
import java.util.UUID;

public interface ProductRepository extends CrudRepository<Product, UUID>, JpaSpecificationExecutor<Product> {

    // HQL
    @Query("select p from Product p where array_contains(p.tags, :tag)")
    Collection<Product> findAllByTagsContaining(@Param("tag") String tag);

    // JPA specification
    default Collection<Product> findAllByTagsContaining_Specification(final String tag) {
        return findAll((root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.isTrue(
                        criteriaBuilder.function("array_contains", Boolean.class,
                                root.get("tags").as(String[].class),
                                criteriaBuilder.literal(tag))
                )
        );
    }

}
