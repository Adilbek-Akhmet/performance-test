package performance.performancetest.product.repositories.mapper;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import org.springframework.stereotype.Component;
import performance.test.reactive.spring.product.dto.CategoryDto;
import performance.test.reactive.spring.product.dto.ProductDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.function.BiFunction;

@Component
public class ProductRowMapper implements BiFunction<Row, RowMetadata, ProductDto> {
    @Override
    public ProductDto apply(Row row, RowMetadata rowMetadata) {
        return ProductDto.builder()
                .id(row.get("id", Long.class))
                .name(row.get("name", String.class))
                .price(row.get("price", BigDecimal.class))
                .description(row.get("description", String.class))
                .manufactureDate(row.get("manufacture_date", LocalDate.class))
                .category(new CategoryDto(row.get("category_id", Long.class), row.get("category_name", String.class), row.get("category_description", String.class)))
                .build();
    }
}
