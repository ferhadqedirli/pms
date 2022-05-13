package com.promex.productionmanagement.controller;

import com.promex.productionmanagement.business.abstracts.ProductService;
import com.promex.productionmanagement.core.DataResult;
import com.promex.productionmanagement.core.Result;
import com.promex.productionmanagement.entities.Product;
import com.promex.productionmanagement.entities.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public Result addProduct(@RequestBody ProductDTO dto) {
        return service.addProduct(dto);
    }

    @PostMapping("/update")
    public Result updateProduct(@RequestBody ProductDTO dto) {
        return service.updateProduct(dto);
    }

    @PostMapping("/remove")
    public Result removeProduct(@RequestParam Integer id) {
        return service.removeProduct(id);
    }

    @GetMapping("/all")
    public DataResult<List<Product>> getAll() {
        return service.getAllByState();
    }

    @GetMapping("/by-id")
    public DataResult<Product> getProductById(@RequestParam Integer id) {
        return service.getProductByProductIdAndState(id);
    }

    @GetMapping("/by-barcode")
    public DataResult<Product> getProductByBarcode(@RequestParam String barcode) {
        return service.getProductByBarcodeAndState(barcode);
    }

    @GetMapping("/by-name")
    public DataResult<List<Product>> getProductsByName(@RequestParam String name) {
        return service.getAllByProductNameStartsWithAndStateOrderByProductName(name);
    }

    @GetMapping("/by-category-name")
    public DataResult<List<Product>> getProductsByCategoryName(@RequestParam String categoryName) {
        return service.getAllByCategory_CategoryNameAndStateOrderByProductName(categoryName);
    }

    @GetMapping("/by-category-id")
    public DataResult<List<Product>> getProductsByCategoryId(@RequestParam Integer categoryId) {
        return service.getAllByCategory_CategoryIdAndStateOrderByProductName(categoryId);
    }

}
