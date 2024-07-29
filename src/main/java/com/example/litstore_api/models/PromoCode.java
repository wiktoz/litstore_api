package com.example.litstore_api.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "promocodes", uniqueConstraints = @UniqueConstraint(columnNames = "promo_code_id"))
public class PromoCode {
    @Id
    @GeneratedValue
    private Integer promo_code_id;
    private String code;
    private Float discount;
    private DiscountUnit unit;
    private String name;
    private String description;
    private Integer current_use;
    private Integer max_use;
    private Date start_date;
    private Date end_date;
}
