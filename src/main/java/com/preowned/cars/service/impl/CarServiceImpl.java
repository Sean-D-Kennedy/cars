package com.preowned.cars.service.impl;

import com.preowned.cars.service.dto.CarDTO;
import com.preowned.cars.repository.entity.Car;
import com.preowned.cars.controller.exception.CarRegNoAlreadyExistsException;
import com.preowned.cars.controller.exception.CarRegNoNotFoundException;
import com.preowned.cars.controller.exception.CarRegNumbersMismatchException;
import com.preowned.cars.service.dto.CarMapper;
import com.preowned.cars.repository.CarRepository;
import com.preowned.cars.service.ICarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service // is a Component
@AllArgsConstructor
public class CarServiceImpl implements ICarService {
    private CarRepository carRepository; // will be injected

    public CarDTO addCar(Car car){
        if(carRepository.findByRegNo(car.getRegNo()).isPresent()) {
            // car reg is already in db, do not add it again
            throw new CarRegNoAlreadyExistsException("Car reg number already exists in db! : "+car.getRegNo());
        }
        Car savedCar = carRepository.save(car); // no need to code save(Car)!
        CarDTO carDto = CarMapper.mapToCarDTO(savedCar, new CarDTO()); // so the primary key is not returned
        return carDto;
    }
    public List<CarDTO> getAllCars(){
        List<CarDTO> listOfCarDtos = new ArrayList<>();
        for(Car car: carRepository.findAll()){
            CarDTO carDto = CarMapper.mapToCarDTO(car, new CarDTO()); // so the primary key is not returned
            listOfCarDtos.add(carDto);
        }
        return listOfCarDtos;
    }
    public CarDTO getCar(String regNo){
        Car car = carRepository
                    .findByRegNo(regNo) // returns Optional<Car>
                    .orElseThrow(() -> new CarRegNoNotFoundException("Car reg number not found in db! : "+regNo));
        CarDTO carDto = CarMapper.mapToCarDTO(car, new CarDTO()); // so the primary key is not returned
        return carDto;
    }
    public List<CarDTO> getAllCarsByBrandName(String brandName){
        List<CarDTO> listOfCarDtos = new ArrayList<>();
        for(Car car: carRepository.findAllByBrandName(brandName)){
            CarDTO carDto = CarMapper.mapToCarDTO(car, new CarDTO()); // so the primary key is not returned
            listOfCarDtos.add(carDto);
        }
        return listOfCarDtos;
    }
    public List<CarDTO> getAllCarsByCarType(String carType){
        List<CarDTO> listOfCarDtos = new ArrayList<>();
        for(Car car: carRepository.findAllByCarType(carType)){
            CarDTO carDto = CarMapper.mapToCarDTO(car, new CarDTO()); // so the primary key is not returned
            listOfCarDtos.add(carDto);
        }
        return listOfCarDtos;
    }
    public boolean deleteCar(String regNo){
        Car car = carRepository
                .findByRegNo(regNo) // returns Optional<Car>
                .orElseThrow(() -> new CarRegNoNotFoundException("Car reg number not found in db! : "+regNo));
        carRepository.deleteByRegNo(regNo); // derived query
        return true;
    }
    public boolean deleteAllCars(){
        carRepository.deleteAll(); // already available
        return true;
    }
    public boolean updateCar(String carRegNoFromURI, Car car){
        if(!carRegNoFromURI.equals(car.getRegNo())) { // reg numbers do not match, generate an exception
            throw new CarRegNumbersMismatchException("Car reg numbers mismatch!. URI: "+carRegNoFromURI+", Entity Body: "+car.getRegNo());
        }
        // this is an update as the regNo is already in the database
        // Update - save() does insert as we know; it will do update if the primary key is present.
        // However, as I am letting the db generate ID's for the primary keys, this will not work.
        // Thus, we need to annotate the update method with @Transaction, @Query and @Modifying.
        // https://stackoverflow.com/questions/11881479/how-do-i-update-an-entity-using-spring-data-jpa
        carRepository.updateCar(car.getBrandName(), car.getModelName(), car.getCarType(),
                car.getYear(), car.getKms(), car.getPrice(), car.getRegNo());
        return true;
    }
}
