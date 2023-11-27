package performance.test.spring.product.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import performance.test.spring.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
