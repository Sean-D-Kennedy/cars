package com.preowned.cars.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

// @Data : Generates getters/setters, a constructor, toString, hashCode and equals.
@Data
public class CarDTO extends RepresentationModel<CarDTO> {
    private String brandName, modelName, regNo, carType;
    private int year, kms, price;
}
