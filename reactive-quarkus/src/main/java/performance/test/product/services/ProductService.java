package performance.test.product.services;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.quarkus.panache.common.Page;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import performance.test.product.dto.ProductDto;
import performance.test.product.mappers.ProductMapper;
import performance.test.product.model.Product;
import performance.test.product.repositories.ProductRepository;

import java.util.List;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository productRepository;

    @WithSession
    public Uni<List<ProductDto>> findAll(int page, int size) {
        return productRepository.findAll()
                .page(Page.of(page, size))
                .list()
                .onItem().transform(products ->
                        products.stream()
                                .map(ProductMapper::toDto)
                                .toList());
    }

    @WithSession
    public Uni<ProductDto> findById(Long id) {
        return productRepository.findById(id).map(ProductMapper::toDto);
    }

    @WithTransaction
    public Uni<ProductDto> save(Product product) {
        return productRepository.persist(product).map(ProductMapper::toDto);
    }
}

