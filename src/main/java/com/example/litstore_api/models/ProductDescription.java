package com.example.litstore_api.models;

import jakarta.persistence.*;

@Entity
@Table(name = "productdescriptions", uniqueConstraints = @UniqueConstraint(columnNames = "product_description_id"))
public class ProductDescription {
    @Id
    @GeneratedValue
    private Integer product_description_id;
    private Integer product_id;
    private Language lang_code;
    private String description;
}
