package performance.test.reactive.spring.product.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import performance.test.reactive.spring.product.model.Product;

public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {
}
