package com.example.litstore_api.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tokens", uniqueConstraints = @UniqueConstraint(columnNames = "token_id"))
public class Token {
    @Id
    @GeneratedValue
    private Integer token_id;
    private String token;
    private Date created_at;
    private Date expiring_at;
}
