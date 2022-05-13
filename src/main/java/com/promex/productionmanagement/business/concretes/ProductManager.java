package com.promex.productionmanagement.business.concretes;

import com.promex.productionmanagement.business.abstracts.ProductService;
import com.promex.productionmanagement.core.DataResult;
import com.promex.productionmanagement.core.Result;
import com.promex.productionmanagement.core.SuccessDataResult;
import com.promex.productionmanagement.database.ProductRepository;
import com.promex.productionmanagement.entities.Product;
import com.promex.productionmanagement.entities.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductManager implements ProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductManager(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result addProduct(ProductDTO dto) {
        Product product = new Product(dto);
        repository.save(product);
        return new SuccessDataResult<>("Product added : ");
    }

    @Override
    public Result updateProduct(ProductDTO dto) {
        Product product = repository.getProductByProductIdAndState(dto.getProductId(), 1);
        product.setProductName(dto.getProductName());
        product.setBarcode(dto.getBarcode());
        product.setCategory(dto.getCategory());
        product.setMeasurement(dto.getMeasurement());
        product.setPrice(dto.getPrice());

        repository.save(product);
        return new SuccessDataResult<>("Product updated : ");
    }

    @Override
    public Result removeProduct(Integer productId) {
        Product product = repository.getProductByProductIdAndState(productId, 1);
        product.setState(0);
        repository.save(product);
        return new SuccessDataResult<>("Product removed : ");
    }

    @Override
    public DataResult<List<Product>> getAllByState() {
        List<Product> products = repository.getAllByState(1);
        return new SuccessDataResult<>(products, "Data listed : ");
    }

    @Override
    public DataResult<Product> getProductByProductIdAndState(Integer productId) {
        Product product = repository.getProductByProductIdAndState(productId, 1);
        return new SuccessDataResult<>(product, "Data listed : ");
    }

    @Override
    public DataResult<Product> getProductByBarcodeAndState(String barcode) {
        Product product = repository.getProductByBarcodeAndState(barcode, 1);
        return new SuccessDataResult<>(product, "Data listed : ");
    }

    @Override
    public DataResult<List<Product>> getAllByProductNameStartsWithAndStateOrderByProductName(String productName) {
        List<Product> products = repository.getAllByProductNameStartsWithAndStateOrderByProductName(productName, 1);
        return new SuccessDataResult<>(products, "Data listed : ");
    }

    @Override
    public DataResult<List<Product>> getAllByCategory_CategoryNameAndStateOrderByProductName(String categoryName) {
        List<Product> products = repository.getAllByCategory_CategoryNameAndStateOrderByProductName(categoryName, 1);
        return new SuccessDataResult<>(products, "Data listed : ");
    }

    @Override
    public DataResult<List<Product>> getAllByCategory_CategoryIdAndStateOrderByProductName(Integer categoryId) {
        List<Product> products = repository.getAllByCategory_CategoryIdAndStateOrderByProductName(categoryId, 1);
        return new SuccessDataResult<>(products, "Data listed : ");
    }
}
