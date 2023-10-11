package tech.aaregall.lab.hibernate6.persistance.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tech.aaregall.lab.hibernate6.persistance.entity.Product;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface ProductRepository extends CrudRepository<Product, UUID> {

    /*
     * org.hibernate.query.SemanticException: Operand of 'member of' operator must be a plural path
     */
    //Collection<Product> findAllByTagsContaining(String tag);

    /*
     * org.hibernate.query.SemanticException: Non-boolean expression used in predicate context: array_contains(p.tags,:tag)
     * Requires FunctionContributor
     */
    @Query("select p from Product p where array_contains(p.tags, :tag)")
    Collection<Product> findAllByTagsContaining(@Param("tag") String tag);


}
