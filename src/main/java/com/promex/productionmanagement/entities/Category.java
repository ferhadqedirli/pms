package com.promex.productionmanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.promex.productionmanagement.entities.dto.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "category")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "products"})
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @Column(nullable = false, unique = true)
    private String categoryName;

    @Column(nullable = false)
    @ColumnDefault("1")
    private int state;

    public Category(CategoryDTO categoryDTO) {
        this.categoryName = categoryDTO.getCategoryName();
    }
}
