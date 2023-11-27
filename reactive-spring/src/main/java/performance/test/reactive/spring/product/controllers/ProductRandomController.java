package performance.test.reactive.spring.product.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import performance.test.reactive.spring.product.dto.ProductDto;
import performance.test.reactive.spring.product.services.ProductService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Random;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products/random")
public class ProductRandomController {

    private final ProductService productService;
    private static final Random random = new Random();


    @GetMapping()
    public Flux<ProductDto> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size
    ) {
        int newPage = random.nextInt(0, 500);
        return productService.findAll(newPage, size);
    }

    @GetMapping("/{id}")
    public Mono<ProductDto> findById(@PathVariable Long id) {
        int newId = random.nextInt(0, 499_999);
        return productService.findById((long) newId);
    }

    @PostMapping
    public Mono<ProductDto> save(@RequestBody ProductDto productDto) {
        return productService.save(productDto);
    }
}
