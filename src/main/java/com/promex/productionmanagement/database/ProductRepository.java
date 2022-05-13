package com.promex.productionmanagement.database;

import com.promex.productionmanagement.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product getProductByProductIdAndState(Integer productId, int state);

    Product getProductByBarcodeAndState(String barcode, int state);

    List<Product> getAllByState(int state);

    List<Product> getAllByProductNameStartsWithAndStateOrderByProductName(String productName, int state);

    List<Product> getAllByCategory_CategoryNameAndStateOrderByProductName(String categoryName, int state);

    List<Product> getAllByCategory_CategoryIdAndStateOrderByProductName(Integer categoryId, int state);

}
