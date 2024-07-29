package com.example.litstore_api.models;

import jakarta.persistence.*;

@Entity
@Table(name = "productphotos", uniqueConstraints = @UniqueConstraint(columnNames = "product_photo_id"))
public class ProductPhoto {
    @Id
    @GeneratedValue
    private Integer product_photo_id;
    private Integer product_id;
    private String url;
    private Integer order_index;
}
