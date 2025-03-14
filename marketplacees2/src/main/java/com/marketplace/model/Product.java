package com.marketplace.model;

import jakarta.persistence.*;
import lombok.*;
import com.marketplace.Enum.ProductEnum;

@Entity
@Table(name="products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String value;

    @Column(nullable = false, unique = true)
    private ProductEnum type;

    @Column(nullable = false, unique = true)
    private String brand;

    @Column(nullable = false)
    private String description;
}
