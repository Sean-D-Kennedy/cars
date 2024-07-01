package com.preowned.cars.repository.entity;

import jakarta.persistence.*;
import lombok.*;

// JPQL = Jakarta Persistence Query Language

@Entity // jakarta
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor // lombok
public class Car {
    @Id // jakarta
    @GeneratedValue(strategy = GenerationType.IDENTITY) // jakarta - use a db id column
    private Long id;

    // @Column required if the member != the db column name i.e. brand_name != brandName
    @Column(name="brand_name") // jakarta
    private String brandName;

    @Column(name="model_name")
    private String modelName;

    @Column(name="reg_no")
    private String regNo;

    @Column(name="car_type")
    private String carType;

    @Column(name="yr")
    private int year;

    private int kms; // table column is "kms" so no need for @Column here
    private int price;
}

