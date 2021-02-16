package course.spring.service;

import course.spring.dao.CategoryRepository;
import course.spring.entity.Category;
import course.spring.exception.NonExisitingEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Transactional(readOnly = true)
@Validated
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new NonExisitingEntityException(String.format("Category with ID='%d' does not exists", id)));
    }

    @Override
    public Category addCategory(Category category) {
        category.setId(null);
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        getCategoryById(category.getId());
        return categoryRepository.save(category);
    }

    @Override
    public Category deleteCategory(Long id) {
        Category deleted = getCategoryById(id);
        categoryRepository.deleteById(id);
        return deleted;
    }
}
