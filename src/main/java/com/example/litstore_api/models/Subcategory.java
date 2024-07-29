package com.example.litstore_api.models;

import jakarta.persistence.*;

@Entity
@Table(name="subcategories", uniqueConstraints = @UniqueConstraint(columnNames = "subcategory_id"))
public class Subcategory {
    @Id
    @GeneratedValue
    private Integer subcategory_id;
    private Integer category_id;
    private String name;
    private String description;
    private String seo_description;
    private String img;
    private String bg_img;
    private boolean display_navbar;
    private boolean display_footer;
    private boolean active;
    private String slug;
}
