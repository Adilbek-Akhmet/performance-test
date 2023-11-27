package performance.test.product.mappers;

import performance.test.product.dto.CategoryDto;
import performance.test.product.model.Category;

public class CategoryMapper {

    private CategoryMapper() {
    }

    public static CategoryDto toDto(Category category) {
        if (category == null) {
            return null;
        }
        return new CategoryDto(category.getId(), category.getName(), category.getDescription());
    }

    public static Category toEntity(CategoryDto categoryDto) {
        if (categoryDto == null) {
            return null;
        }
        return new Category(categoryDto.getId(), categoryDto.getName(), categoryDto.getDescription());
    }
}

