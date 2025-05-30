package com.tuyenvp.spring_boot_app.Services;

import com.tuyenvp.spring_boot_app.Model.Category;
import com.tuyenvp.spring_boot_app.dto.request.CategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CategoryService {
    public List<Category> ListCategory();
    Optional<Category> findCategoryById(Long category_id);
    public Category addCategory(Category add_category);
    public Category updateCategory(Long id, CategoryDTO categoryDTO);
    public Category deleteCategory(Long category_id);
    public List<Category> searchCategory(String keyword);
    Page<Category> getAll(Integer pageNo);
    List<Category> getAllCategory();
    Page<Category> searchCategory(String keyword, Integer pageNo);
    //public Category getCategoryById(int category_id);
}
