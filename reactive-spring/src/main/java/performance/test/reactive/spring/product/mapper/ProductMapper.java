package performance.test.reactive.spring.product.mapper;

import performance.test.reactive.spring.product.dto.CategoryDto;
import performance.test.reactive.spring.product.dto.ProductDto;
import performance.test.reactive.spring.product.model.Category;
import performance.test.reactive.spring.product.model.Product;

public class ProductMapper {

    private ProductMapper() {
    }

    public static ProductDto toDto(Product product, Category category) {
        CategoryDto categoryDto = new CategoryDto();
        if (category != null) {
            categoryDto.setId(category.getId());
            categoryDto.setName(category.getName());
            categoryDto.setDescription(category.getDescription());
        } else {
            categoryDto.setId(product.getId());
        }

        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .manufactureDate(product.getManufactureDate())
                .category(categoryDto)
                .build();
    }

    public static Product toEntity(ProductDto dto) {
        Product.ProductBuilder builder = Product.builder()
                .id(dto.getId())
                .name(dto.getName())
                .price(dto.getPrice())
                .description(dto.getDescription())
                .manufactureDate(dto.getManufactureDate());

        if (dto.getCategory() != null) {
            builder.categoryId(dto.getCategory().getId());
        }

        return builder.build();
    }


}
