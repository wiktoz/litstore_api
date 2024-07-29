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
@Table(name="addresses", uniqueConstraints = @UniqueConstraint(columnNames = "address_id"))
public class Address {
    @Id
    @GeneratedValue
    private Integer address_id;
    private Integer user_id;
    private String name;
    private String surname;
    private String street;
    private String house;
    private String flat;
    private String post_code;
    private String city;
    private String phone;
    private String country;
}
