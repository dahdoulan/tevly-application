package org.group15.tveely.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="categories")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private Long id;

    @Column(name="category",unique = true, nullable = false)
    private Long category;
}
