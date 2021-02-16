package course.spring.service;

import course.spring.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();

    Category getCategoryById(Long id);

    Category addCategory(Category category);

    Category updateCategory(Category category);

    Category deleteCategory(Long id);

    long getCategoriesCount();

}
