package com.example.litstore_api.models;

import jakarta.persistence.*;

@Entity
@Table(name = "varianttypes", uniqueConstraints = @UniqueConstraint(columnNames = "variant_type_id"))
public class VariantType {
    @Id
    @GeneratedValue
    private Integer variant_type_id;
    private String name;
    private String display_name;
    private SelectionType selection_type;
    private String slug;
}
