package com.promex.productionmanagement.entities.dto;

import com.promex.productionmanagement.entities.Category;
import com.promex.productionmanagement.entities.Measurement;
import com.promex.productionmanagement.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ProductDTO {
    private Integer productId;
    private String barcode;
    private String productName;
    private Category category;
    private Measurement measurement;
    private Double cost;
    private Double price;
    private Double quantity;

    private int state = 1;

    public ProductDTO(Product product) {
        this.productId = product.getProductId();
        this.barcode = product.getBarcode();
        this.productName = product.getProductName();
        this.category = product.getCategory();
        this.measurement = product.getMeasurement();
        this.price = product.getPrice();
    }
}
