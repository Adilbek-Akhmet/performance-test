package performance.test.reactive.spring.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("products")
public class Product {
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private LocalDate manufactureDate;
    private Long categoryId;
}

