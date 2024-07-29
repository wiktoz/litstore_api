package com.example.litstore_api.models;

import jakarta.persistence.*;

@Entity
@Table(name = "permissions", uniqueConstraints = @UniqueConstraint(columnNames = "permission_id"))
public class Permission {
    @Id
    @GeneratedValue
    private Integer permission_id;
    private String name;
}
