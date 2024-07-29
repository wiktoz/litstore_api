package com.example.litstore_api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="categories", uniqueConstraints = @UniqueConstraint(columnNames = "category_id"))
public class Category {
    @Id
    @GeneratedValue
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
