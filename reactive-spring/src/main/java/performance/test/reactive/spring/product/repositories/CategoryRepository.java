package performance.test.reactive.spring.product.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import performance.test.reactive.spring.product.model.Category;

public interface CategoryRepository extends ReactiveCrudRepository<Category, Long> {
}
