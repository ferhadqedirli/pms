package com.promex.productionmanagement.business.concretes;

import com.promex.productionmanagement.business.abstracts.CategoryService;
import com.promex.productionmanagement.core.*;
import com.promex.productionmanagement.database.CategoryRepository;
import com.promex.productionmanagement.entities.Category;
import com.promex.productionmanagement.entities.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryManager implements CategoryService {
    private final CategoryRepository repository;

    @Autowired
    public CategoryManager(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result addCategory(CategoryDTO dto) {
        try {
            Category category = new Category(dto);
            category.setState(1);
            repository.save(category);
            return new SuccessResult("Category added :");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ErrorResult("Category not added : Please try again");
        }
    }

    @Override
    public Result updateCategory(CategoryDTO dto) {
        try {
            Category category1 = repository.getCategoryByCategoryIdAndState(dto.getCategoryId(), 1);
            category1.setCategoryName(dto.getCategoryName());
            repository.save(category1);
            return new SuccessResult("Category updated :");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ErrorResult("Category not update :Please try again");
        }
    }

    @Override
    public Result removeCategory(Integer id) {
        try {
            Category category = repository.getCategoryByCategoryIdAndState(id, 1);
            category.setState(0);
            repository.save(category);
            return new SuccessResult("Category deleted :");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ErrorResult("The ID you entered is incorrect. Please try again");
        }
    }

    @Override
    public DataResult<Category> getCategoryByCategoryIdAndState(Integer categoryId) {
        try {
            return new SuccessDataResult<>(repository.getCategoryByCategoryIdAndState(categoryId, 1), "Data listed :");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ErrorDataResult<>("The ID you entered is incorrect. Please try again");
        }
    }

    @Override
    public DataResult<List<Category>> getAllByCategoryNameStartsWithAndStateOrderByCategoryName(String prefix) {
        try {
            return new SuccessDataResult<>(repository.getAllByCategoryNameStartsWithAndStateOrderByCategoryName(prefix, 1), "Data listed :");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ErrorDataResult<>("Nothing found in this name.Please try again");
        }
    }

    @Override
    public DataResult<List<Category>> getAllByStateOrderByCategoryName() {

        return new SuccessDataResult<>(repository.getAllByStateOrderByCategoryName(1), "Data listed :");
    }


}
