package com.example.realestate.service;

import com.example.realestate.dao.HouseRepo;
import com.example.realestate.dao.HouseRepository;
import com.example.realestate.model.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
//Implements the interface HouseService
public class HouseServiceImplementation{

//    House house = new House(453,BEDSITTER,"Njokerio",3500.00,"Breeze","Booked","A6");
    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private HouseRepo houseRepo;

    //The method will basically return a list of all houses to the controller
    public List<House> getAllHouses() {
        return houseRepository.findAll();
    }

    //Save Method for houses
    public House saveHouse(House house){

        return houseRepository.save(house);
    }

    //Get method by house code
    public Optional<House> getHouseByCode(Integer houseCode){

        return houseRepository.findById(houseCode);
    }

    //Delete method using houseCode
    public void deleteHouse(Integer houseCode){
        houseRepository.findById(houseCode).ifPresent(houseRepository::delete);
    }

    public House findHousesById(Integer houseCode) {
        return houseRepository.findById(houseCode).orElseThrow();
    }

//    // search functionality (to take keyword as a parameter)
//    public List<House> listAll(String keyword){
//        if (keyword != null){
//            return houseRepo.search(keyword);
//        }
//        return houseRepo.findAll();
//    }

    //pagination method
//    public List<House> listAll(Integer pageNo, Integer pageSize, String sortBy) {
//        Pageable pageable = (Pageable) PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending()); //pagination with sorting in ascending order
//        Page<House> pagedResult = houseRepo.findAll((org.springframework.data.domain.Pageable) pageable);
//
//        if (pagedResult.hasContent()){
//            return pagedResult.getContent();
//        }else {
//            return new ArrayList<House>();
//        }
//    }
}
