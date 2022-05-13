package com.promex.productionmanagement.database;

import com.promex.productionmanagement.entities.ProductWarehouse;
import com.promex.productionmanagement.entities.dto.WarehouseFifoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductWarehouseRepository extends JpaRepository<ProductWarehouse, Long> {

    List<ProductWarehouse> getByProduct_ProductIdOrderByDateTime(Integer productId);

    List<ProductWarehouse> getAllByDateTime(LocalDateTime dateTime);

    @Query("SELECT pw FROM ProductWarehouse pw WHERE pw.dateTime <= ?1 ORDER BY pw.dateTime")
    List<ProductWarehouse> getAllByGivenDateTime(LocalDateTime dateTime);

    @Query("Select new com.promex.productionmanagement.entities.dto.WarehouseFifoDTO(p.barcode, p.productName, c.categoryName, sum(pw.quantity)," +
            "m.measurementName, (sum(pw.amount)/sum(pw.quantity)) as cost, sum(pw.amount), w.warehouseName) " +
            " From ProductWarehouse pw Left Join Product p on p.productId=pw.product.productId" +
            " Left join Category c on c.categoryId=p.category.categoryId" +
            " Left join Measurement m on m.measurementId=p.measurement.measurementId" +
            " Left join Warehouse w on w.warehouseId=pw.warehouse.warehouseId where pw.dateTime <= ?1 group by p.barcode")
    List<WarehouseFifoDTO> getAllWarehouseDataFifo(LocalDateTime dateTime);

}
