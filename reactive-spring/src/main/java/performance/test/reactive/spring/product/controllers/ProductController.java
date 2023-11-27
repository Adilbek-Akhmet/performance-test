package performance.test.reactive.spring.product.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import performance.test.reactive.spring.product.dto.ProductDto;
import performance.test.reactive.spring.product.services.ProductService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    public Flux<ProductDto> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size
    ) {
        return productService.findAll(page, size);
    }

    @GetMapping("/{id}")
    public Mono<ProductDto> findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PostMapping
    public Mono<ProductDto> save(@RequestBody ProductDto productDto) {
        return productService.save(productDto);
    }
}
