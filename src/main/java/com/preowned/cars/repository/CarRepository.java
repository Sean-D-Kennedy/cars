package com.preowned.cars.repository;

import com.preowned.cars.repository.entity.Car;

// Methods inherited from interface org.springframework.data.repository.CrudRepository:
//    count, delete, deleteAll, deleteAllById, deleteById, existsById, findById, save
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

// This is where I inform JPA of my type/table. JPA can look up my Car entity and work from there.
// The implementation of this interface is done by the JPA (in the background)!
public interface CarRepository extends JpaRepository<Car, Long> {
    // The methods defined here are custom methods that the JPA will implement for me, assuming I name
    // them properly. These are in addition to the other methods already available e.g. save().

    Optional<Car> findByRegNo(String regNo); // must have a property "regNo" in Car
    // no method save(Car) defined, framework provides by default

    List<Car> findAllByBrandName(String brandName); // must have a property "brandName" in Car
    List<Car> findAllByCarType(String carType); // must have a property "carType" in Car

    @Transactional  // jakarta, REQUIRED, this method is done completely or not at all
    void deleteByRegNo(String regNo);

    @Transactional  // jakarta, REQUIRED, this method is done completely or not at all
    @Modifying
    @Query("update Car c set c.brandName = ?1, c.modelName = ?2, c.carType = ?3, c.year = ?4, c.kms = ?5, c.price = ?6 where c.regNo = ?7")
    void updateCar(String brandName, String modelName, String carType,
                   Integer year, Integer kms, Integer price, String regNo);

}
