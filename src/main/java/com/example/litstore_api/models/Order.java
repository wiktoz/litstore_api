package com.example.litstore_api.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "orders", uniqueConstraints = @UniqueConstraint(columnNames = "order_id"))
public class Order {
    @Id
    @GeneratedValue
    private Integer order_id;
    private Integer address_id;
    private Integer delivery_id;
    private Integer delivery_address_id;
    private Integer promo_code_id;
    private Integer user_id;
    private String guest_email;
    private Float discount_amount;
    private Float payment_amount;
    private Currency payment_currency;
    private String payment_method;
    private String payment_hash;
    private String payment_url;
    private PaymentStatus payment_status;
    private OrderStatus status;
    private Date created_at;
    private Date updated_at;
}
