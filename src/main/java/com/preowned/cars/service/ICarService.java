package com.preowned.cars.service;

import com.preowned.cars.service.dto.CarDTO;
import com.preowned.cars.repository.entity.Car;

import java.util.List;

public interface ICarService {
    // POST
    CarDTO addCar(Car car);

    // GET
    public List<CarDTO> getAllCars();
    public CarDTO getCar(String regNo);
    public List<CarDTO> getAllCarsByBrandName(String brandName);
    public List<CarDTO> getAllCarsByCarType(String carType);

    // DELETE
    boolean deleteCar(String regNo);
    boolean deleteAllCars();

    // PUT
    public boolean updateCar(String carRegNo, Car car);
}
