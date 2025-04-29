package com.tuyenvp.spring_boot_app.Controller.Admin;

import com.tuyenvp.spring_boot_app.Model.Category;
import com.tuyenvp.spring_boot_app.Services.Impl.CategoryServiceImpl;
import com.tuyenvp.spring_boot_app.dto.request.CategoryDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/admin/")
public class CategoryController {
    @Autowired
    public CategoryServiceImpl categoryServiceImpl;
    @GetMapping("/category")
    public String category(Model model,
                           @Param(value="keyword") String keyword ,
                           @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        Page<Category>list = categoryServiceImpl.getAll(pageNo);

        if(keyword != null && !keyword.isEmpty()) {
            list = categoryServiceImpl.searchCategory(keyword, pageNo);
            model.addAttribute("keyword", keyword);
        }else{
            list = categoryServiceImpl.getAll(pageNo);
        }

        model.addAttribute("totalPage", list.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("ListCategory",list);
        return "admin/category/category";
    }

    // thêm danh mục
    @GetMapping(value = "/category/add_category")
    public String add_category(Model model){
        Category add_category = new Category();
        model.addAttribute("add_category", add_category);
        return "admin/category/add_category";
    }
    @PostMapping("/category/add_category")
    public String save_category(Model model,@Valid @ModelAttribute("category") Category save_category , BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "admin/category/add_category";
        }
        categoryServiceImpl.addCategory(save_category);
        return "redirect:/admin/category";
    }

    // sửa danh mục
    @GetMapping("/category/edit_category/{id}")
    public String edit_category(
            Model model,
            @PathVariable("id")Long id){
        Optional<Category>category = categoryServiceImpl.findCategoryById(id);
        model.addAttribute("edit_category", category.get());
        return "admin/category/edit_category";
    }
    @PutMapping("/category/{id}")
    public String update_category(
            @PathVariable Long id,
            @Valid @RequestBody CategoryDTO categoryDTO,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/category/edit_category";
        }
        categoryServiceImpl.updateCategory(id, categoryDTO);
        return "redirect:/admin/category";
    }

    @DeleteMapping("/category/{id}")
    public String del_category(
            @PathVariable Long id){
        categoryServiceImpl.deleteCategory(id);
        return "redirect:/admin/category";
    }
}
