package performance.test.reactive.spring.product.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import performance.test.reactive.spring.product.dto.ProductDto;
import performance.test.reactive.spring.product.mapper.ProductMapper;
import performance.test.reactive.spring.product.repositories.mapper.ProductRowMapper;
import performance.test.reactive.spring.product.model.Product;
import performance.test.reactive.spring.product.repositories.CategoryRepository;
import performance.test.reactive.spring.product.repositories.ProductRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;

@Service
public record ProductService(
        ProductRepository productRepository,
        CategoryRepository categoryRepository,
        DatabaseClient databaseClient,
        ProductRowMapper productRowMapper
) {

    private static final String FIND_ALL_PRODUCTS_QUERY = """
            SELECT p.*, c.id as category_id, c.name as category_name, c.description as category_description
            FROM products p
            JOIN categories c ON c.id = p.category_id
            LIMIT :size OFFSET :offset
            """;

    private static final String FIND_PRODUCT_BY_ID_QUERY = """
            select p.*, c.id as category_id, c.name as category_name, c.description as category_description 
            from products p
            join categories c on c.id = p.category_id
            where p.id = :productId
            """;

    public Flux<ProductDto> findAll(Integer page, Integer size) {
        return databaseClient.sql(FIND_ALL_PRODUCTS_QUERY)
                .bind("size", size)
                .bind("offset", size * page)
                .map(productRowMapper).all();
    }

    public Mono<ProductDto> findById(Long id) {
        return databaseClient.sql(FIND_PRODUCT_BY_ID_QUERY)
                .bind("productId", id)
                .map(productRowMapper)
                .one();
    }

    public Mono<ProductDto> save(ProductDto productDto) {
        return productRepository.save(ProductMapper.toEntity(productDto))
                .map(it -> ProductMapper.toDto(it, null));
    }

    public Mono<Product> update(Long id, Product product) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new NoSuchElementException("Product with id " + id + " not found")))
                .flatMap(it -> productRepository.save(product));
    }
}
