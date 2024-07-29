package com.example.litstore_api.models;

import jakarta.persistence.*;

@Entity
@Table(name = "items", uniqueConstraints = @UniqueConstraint(columnNames = "item_id"))
public class Item {
    @Id
    @GeneratedValue
    private Integer item_id;
    private Integer product_id;
    private Integer variant_option_id;
    private Float price;
    private Integer stock;
    private ItemUnit unit;
    private String sku;
}
