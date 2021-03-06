package com.example.realestate.controller;


import com.example.realestate.dao.HouseRepo;
import com.example.realestate.model.House;
import com.example.realestate.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/houses")
public class HouseControllerII {
    @Autowired
    private HouseRepo houseRepo;


    @GetMapping
    public List<House> findAllHouses(){
        return houseRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findHouseById(@PathVariable(value = "id") long id) {
        verifyHouse(id);
        House house = houseRepo.getOne(id);
        return new ResponseEntity<>(house, HttpStatus.OK);
    }

    @GetMapping("/notbooked")
    public List<House> findAllUnBooked(){
        return houseRepo.findAllUnBooked();
    }

    @GetMapping("/recent")
    public List<House> findRecent(){
        return houseRepo.findRecent();
    }

    @PostMapping
    public House saveHouse(@Validated @RequestBody House house){
        return houseRepo.save(house);
    }

    @PostMapping("/{id}/book/{slots}")
    public void bookHouse(@Validated @PathVariable(value = "id") long id, @PathVariable( value = "slots") int slots ){
        verifyHouse(id);
        houseRepo.bookHouse(slots, id);
    }

    @PutMapping
    public House updateHouse(@Validated @RequestBody House house) {
        verifyHouse(house.getId());
        return houseRepo.save(house);
    }

    @DeleteMapping("/{id}/delete")
    public void deleteHouse(@PathVariable(value = "id") long id){
        verifyHouse(id);
        houseRepo.deleteById(id);
    }

    @DeleteMapping()
    public void deleteAllHouses(){
        houseRepo.deleteAll();
    }

    //checks if house exists
    private void verifyHouse(long house_id) throws ResourceNotFoundException {
        Optional<House> house = houseRepo.findById(house_id);
        if(house.isEmpty()){
            throw  new ResourceNotFoundException("House with id " + house_id + " not found");
        }
    }
    

    
}
