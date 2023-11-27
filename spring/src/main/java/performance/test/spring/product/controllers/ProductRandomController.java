package performance.test.spring.product.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import performance.test.spring.product.dto.ProductDto;
import performance.test.spring.product.services.ProductService;

import java.util.List;
import java.util.Random;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products/random")
public class ProductRandomController {

    private final ProductService productService;
    private static final Random random = new Random();

    @GetMapping
    public List<ProductDto> findAll(@PageableDefault(size = 50, page = 0) Pageable pageable) {
        int newPage = random.nextInt(0, 500);
        return productService.findAll(PageRequest.of(newPage, pageable.getPageSize()));
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        int newId = random.nextInt(0, 499_999);
        return productService.findById((long) newId);
    }

    @PostMapping
    public ProductDto save(@RequestBody ProductDto productDto) {
        return productService.save(productDto);
    }
}
