package com.promex.productionmanagement.business.abstracts;

import com.promex.productionmanagement.core.DataResult;
import com.promex.productionmanagement.core.Result;
import com.promex.productionmanagement.entities.Product;
import com.promex.productionmanagement.entities.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    Result addProduct(ProductDTO dto);

    Result updateProduct(ProductDTO dto);

    Result removeProduct(Integer productId);

    DataResult<List<Product>> getAllByState();

    DataResult<Product> getProductByProductIdAndState(Integer productId);

    DataResult<Product> getProductByBarcodeAndState(String barcode);

    DataResult<List<Product>> getAllByProductNameStartsWithAndStateOrderByProductName(String productName);

    DataResult<List<Product>> getAllByCategory_CategoryNameAndStateOrderByProductName(String categoryName);

    DataResult<List<Product>> getAllByCategory_CategoryIdAndStateOrderByProductName(Integer categoryId);

}
