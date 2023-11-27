package performance.test.spring.product.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import performance.test.spring.product.dto.ProductDto;
import performance.test.spring.product.mapper.ProductMapper;
import performance.test.spring.product.repositories.ProductRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductDto> findAll(Pageable pageable) {
        return productRepository.findAll(pageable).stream().map(ProductMapper::toDto).toList();
    }

    public ProductDto findById(Long id) {
        return productRepository.findById(id)
                .map(ProductMapper::toDto)
                .orElseThrow(() -> new NoSuchElementException("Product not found with id " + id));
    }

    public ProductDto save(ProductDto productDto) {
        return ProductMapper.toDto(productRepository.save(ProductMapper.toEntity(productDto)));
    }
}
