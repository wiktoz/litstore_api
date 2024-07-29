package com.example.litstore_api.models;

import jakarta.persistence.*;

@Entity
@Table(name = "variantoptions", uniqueConstraints = @UniqueConstraint(columnNames = "variant_option_id"))
public class VariantOption {
    @Id
    @GeneratedValue
    private Integer variant_option_id;
    private Integer variant_type_id;
    private String name;
    private Integer order_index;
}
