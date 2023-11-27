package performance.performancetest;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import performance.performancetest.product.repositories.CategoryRepository;
import performance.performancetest.product.repositories.ProductRepository;

import java.util.Random;

@SpringBootApplication
@RequiredArgsConstructor
public class PerformanceTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(PerformanceTestApplication.class, args);
    }

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final R2dbcEntityTemplate template;

    private static final Random random = new Random();

    @PostConstruct
    void init() {
//        productRepository.deleteAll().subscribe();
//        categoryRepository.deleteAll().subscribe();
//
//        Flux.range(0, 10)
//                .map(i -> new Category(Long.valueOf(i), "Category " + i, generateRandomText(200)))
//                .flatMap(categoryRepository::save)  // Using flatMap for concurrent inserts
//                .subscribe();
//
//        Flux.range(0, 500_000)
//                .map(i -> new Product(null, "Product " + i, BigDecimal.valueOf(generateRandomInt(1_000_000)), generateRandomText(255), LocalDate.of(2023, 11, generateRandomInt(10) + 1), Long.valueOf(generateRandomInt(10))))
//                .flatMap(productRepository::save)
//                .onBackpressureBuffer()
//                .subscribe();
    }

    public static Integer generateRandomInt(Integer max) {
        return random.nextInt(max);
    }

    public static String generateRandomText(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder text = new StringBuilder();

        for (int i = 0; i < length; i++) {
            text.append(characters.charAt(random.nextInt(characters.length())));
        }

        return text.toString();
    }

}
