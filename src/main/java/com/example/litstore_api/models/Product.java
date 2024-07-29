package com.example.litstore_api.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "products", uniqueConstraints = @UniqueConstraint(columnNames = "product_id"))
public class Product {
    @Id
    @GeneratedValue
    private Integer product_id;
    private Integer category_id;
    private Integer subcategory_id;
    private String name;
    private String manufacturer;
    private boolean is_new;
    private boolean active;
    private String slug;
    private Date created_at;
    private Date updated_at;
}
