package com.example.litstore_api.models;

import jakarta.persistence.*;

@Entity
@Table(name = "deliveries", uniqueConstraints = @UniqueConstraint(columnNames = "delivery_id"))
public class Delivery {
    @Id
    @GeneratedValue
    private Integer delivery_id;
    private String name;
    private String description;
    private String img;
    private Float price;
    private Float free_from;
    private boolean personal_collect;
    private boolean cash_on_delivery;
    private boolean active;
    private String slug;
}
