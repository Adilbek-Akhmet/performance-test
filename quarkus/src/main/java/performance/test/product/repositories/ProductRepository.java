package performance.test.product.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import performance.test.product.model.Product;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product> {
}
