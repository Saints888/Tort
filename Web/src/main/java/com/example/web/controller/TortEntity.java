package com.example.web.controller;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "torts")

public class TortEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    private String author;

    private String title;

    private Double price;
}
