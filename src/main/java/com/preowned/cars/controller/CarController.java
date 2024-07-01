package com.preowned.cars.controller;

import com.preowned.cars.service.dto.CarDTO;
import com.preowned.cars.repository.entity.Car;
import com.preowned.cars.service.ICarService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType; // make sure it is this one!
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController // is a Component
@RequestMapping(path="/cars", produces = MediaType.APPLICATION_JSON_VALUE) // works
@AllArgsConstructor
public class CarController {
    private ICarService iCarService; // injected due to @RestController (which maps to @Component), @AllArgsConstructor

    //       /cars
    @GetMapping
    public List<CarDTO> getAllCars(){return iCarService.getAllCars();}


    @PostMapping
    public ResponseEntity<CarDTO> addCar(@RequestBody Car car, UriComponentsBuilder uriComponentsBuilder) {
        System.out.println("XXX car is "+car);
        CarDTO carDto = iCarService.addCar(car);

        URI locationURI = uriComponentsBuilder
                .path("/cars/"+carDto.getRegNo())
                .buildAndExpand(uriComponentsBuilder.toUriString())
                .toUri();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(locationURI)
                .body(carDto);
    }
    @DeleteMapping
    public ResponseEntity<String> deleteAllCars(){
        iCarService.deleteAllCars();
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT) // success, no message body
                .build();
    }
    @PutMapping
    public ResponseEntity<String> putNotSupported(){
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .build();
    }


    @GetMapping(path = "/car", params="brandName")
    public List<CarDTO> getAllCarsByBrand(@RequestParam String brandName){ // must be ?brandName=XX in Postman
        return iCarService.getAllCarsByBrandName(brandName);
    }

    @GetMapping(path = "/car", params="carType")
    public List<CarDTO> getAllCarsByCarType(@RequestParam String carType){ // must be ?carType=XX in Postman
        return iCarService.getAllCarsByCarType(carType);
    }

    //       /{carRegNo}
    @GetMapping("/{carRegNo}")
    public ResponseEntity<CarDTO> getCarDetailsPath(@PathVariable String carRegNo){
        CarDTO carDto = iCarService.getCar(carRegNo);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carDto);
    }

    @DeleteMapping("/{carRegNo}")
    public ResponseEntity<String> deleteCarDetails(@PathVariable String carRegNo){
        iCarService.deleteCar(carRegNo);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT) // success, no message body
                .build();
    }
    @PostMapping("/{carRegNo}")
    public ResponseEntity<String> postNotSupported(){
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .build();
    }

    @PutMapping("/{carRegNo}")
    public ResponseEntity<CarDTO> updateCar(@PathVariable String carRegNo, @RequestBody Car car){
        iCarService.updateCar(carRegNo, car);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT) // no message body
                .build();
    }
//    @OptionsMapping  - not available
//    @HeadMapping     - not available
    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<String> optionsCollectionOfCars() {  //      /cars
        return ResponseEntity
                .status(HttpStatus.OK) // 200 OK
                .allow(HttpMethod.HEAD, HttpMethod.GET, HttpMethod.POST, HttpMethod.DELETE) // varargs list
                .build();

    }
   @RequestMapping(value = "/{carRegNo}", method = RequestMethod.OPTIONS)
    public ResponseEntity<String> optionsIndividualCar() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .allow(HttpMethod.HEAD, HttpMethod.GET, HttpMethod.PUT, HttpMethod.DELETE)
                .build();

    }
    @RequestMapping(value = "/car", method = RequestMethod.OPTIONS)
    public ResponseEntity<String> optionsIndividualRequestParams() {
        return ResponseEntity
                .status(HttpStatus.OK) // success
                .allow(HttpMethod.HEAD, HttpMethod.GET)
                .build();

    }
}
