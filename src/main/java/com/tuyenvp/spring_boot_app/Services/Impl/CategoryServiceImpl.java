package com.tuyenvp.spring_boot_app.Services.Impl;

import com.tuyenvp.spring_boot_app.Model.Category;
import com.tuyenvp.spring_boot_app.Repository.DbConnect;
import com.tuyenvp.spring_boot_app.Services.CategoryService;
import com.tuyenvp.spring_boot_app.dto.request.CategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final DbConnect DbConnect;

    @Override
    public List<Category> ListCategory() {
        return DbConnect.categoryRepo.findAll();
    }

    @Override
    public Optional<Category> findCategoryById(Long category_id) {
        return DbConnect.categoryRepo.findById(category_id);
    }

    @Override
    public Category addCategory(Category add_category) {
        return DbConnect.categoryRepo.save(add_category);
    }

    @Override
    public Category updateCategory(Long id, CategoryDTO categoryDTO) {
        Optional<Category>category = DbConnect.categoryRepo.findById(id);
        if (category.isEmpty()) {
            return null;
        }
        Category updateCategory = Category.builder()
                .category_name(categoryDTO.getCategory_name())
                .build();
        // cập nhật tên danh mục
        DbConnect.categoryRepo.save(updateCategory);
        return updateCategory;
    }

    @Override
    public Category deleteCategory(Long category_id) {
        Optional<Category>category = DbConnect.categoryRepo.findById(category_id);
        if(category.isEmpty()) {
            return null;
        }
        DbConnect.categoryRepo.deleteById(category_id);
        return category.get();
    }

    @Override
    public List<Category> searchCategory(String keyword) {
        return DbConnect.categoryRepo.searchCategory(keyword);
    }

    @Override
    public Page<Category> getAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo-1, 4);
        return DbConnect.categoryRepo.findAll(pageable);
    }

    @Override
    public List<Category> getAllCategory() {
        List<Category> categories = DbConnect.categoryRepo.findAll();
        return categories;
    }

    @Override
    public Page<Category> searchCategory(String keyword, Integer pageNo) {
        List list = searchCategory(keyword);
        Pageable pageable = PageRequest.of(pageNo-1, 4);
        Integer start = (int) pageable.getOffset();
        Integer end =(int) ((pageable.getOffset() + pageable.getPageSize()) > list.size()
                            ? list.size()
                            : pageable.getOffset() + pageable.getPageSize());
        list = list.subList(start, end);
        return new PageImpl<Category>(list, pageable, searchCategory(keyword).size());
    }

    /*@Override
    public Category getCategoryById(int category_id) {
        return DbConnect.categoryRepo.getById(category_id);
    }*/

}
