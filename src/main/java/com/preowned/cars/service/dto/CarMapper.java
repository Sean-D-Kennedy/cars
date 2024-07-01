package com.preowned.cars.service.dto;

import com.preowned.cars.repository.entity.Car;

public class CarMapper {
    public static CarDTO mapToCarDTO(Car car, CarDTO carDto) {
        carDto.setBrandName(car.getBrandName());
        carDto.setModelName(car.getModelName());
        carDto.setRegNo(car.getRegNo());
        carDto.setCarType(car.getCarType());
        carDto.setYear(car.getYear());
        carDto.setKms(car.getKms());
        carDto.setPrice(car.getPrice());

        return carDto;
    }
}
