package performance.test.product.services;

import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import performance.test.product.dto.ProductDto;
import performance.test.product.mappers.ProductMapper;
import performance.test.product.model.Product;
import performance.test.product.repositories.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository productRepository;

    public List<ProductDto> findAll(int page, int size) {
        return productRepository.findAll()
                .page(Page.of(page, size))
                .stream()
                .map(ProductMapper::toDto)
                .collect(Collectors.toList());
    }

    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id);
        return ProductMapper.toDto(product);
    }

    @Transactional
    public ProductDto save(ProductDto productDto) {
        Product product = ProductMapper.toEntity(productDto);
        productRepository.persist(product);
        return ProductMapper.toDto(product);
    }
}

