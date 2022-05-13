package com.promex.productionmanagement.database;

import com.promex.productionmanagement.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category getCategoryByCategoryIdAndState(Integer categoryId, int state);

    List<Category> getAllByCategoryNameStartsWithAndStateOrderByCategoryName(String categoryName, int state);

    List<Category> getAllByStateOrderByCategoryName(int state);


}
