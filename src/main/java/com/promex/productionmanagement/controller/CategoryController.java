package com.promex.productionmanagement.controller;

import com.promex.productionmanagement.business.abstracts.CategoryService;
import com.promex.productionmanagement.core.DataResult;
import com.promex.productionmanagement.core.Result;
import com.promex.productionmanagement.entities.Category;
import com.promex.productionmanagement.entities.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add")
    public Result addCategory(@RequestBody CategoryDTO dto) {
        return categoryService.addCategory(dto);
    }

    @PostMapping("/update")
    public Result updateCategory(@RequestBody CategoryDTO dto) {
        return categoryService.updateCategory(dto);
    }

    @PostMapping("/delete")
    public Result removeCategory(@RequestParam Integer id) {
        return categoryService.removeCategory(id);
    }

    @GetMapping("/getAllByStateOrderByCategoryName")
    public DataResult<List<Category>> getAllByStateOrderByCategoryName() {
        return categoryService.getAllByStateOrderByCategoryName();
    }

    @GetMapping("/getCategoryByCategoryIdAndState")
    public DataResult<Category> getCategoryByCategoryIdAndState(@RequestParam Integer categoryId) {
        return categoryService.getCategoryByCategoryIdAndState(categoryId);
    }

    @GetMapping("/getAllByCategoryNameStartsWith")
    public DataResult<List<Category>> getAllByCategoryNameStartsWithAndStateOrderByCategoryName(@RequestParam String prefix) {
        return categoryService.getAllByCategoryNameStartsWithAndStateOrderByCategoryName(prefix);
    }
}
