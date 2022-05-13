package com.promex.productionmanagement.business.abstracts;

import com.promex.productionmanagement.core.DataResult;
import com.promex.productionmanagement.core.Result;
import com.promex.productionmanagement.entities.Category;
import com.promex.productionmanagement.entities.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    Result addCategory(CategoryDTO dto);

    Result updateCategory(CategoryDTO dto);

    Result removeCategory(Integer id);

    DataResult<Category> getCategoryByCategoryIdAndState(Integer categoryId);

    DataResult<List<Category>> getAllByCategoryNameStartsWithAndStateOrderByCategoryName(String prefix);

    DataResult<List<Category>> getAllByStateOrderByCategoryName();


}
