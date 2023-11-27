package performance.test.product.mappers;


import performance.test.product.dto.ProductDto;
import performance.test.product.model.Product;

public class ProductMapper {

    private ProductMapper() {
    }

    public static ProductDto toDto(Product product) {
        if (product == null) {
            return null;
        }

        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        dto.setManufactureDate(product.getManufactureDate());
        dto.setCategory(CategoryMapper.toDto(product.getCategory()));

        return dto;
    }

    public static Product toEntity(ProductDto dto) {
        if (dto == null) {
            return null;
        }

        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        product.setManufactureDate(dto.getManufactureDate());
        product.setCategory(CategoryMapper.toEntity(dto.getCategory()));

        return product;
    }
}

