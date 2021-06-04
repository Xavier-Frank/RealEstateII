package com.example.realestate.controller;


import com.example.realestate.model.House;
import com.example.realestate.service.HouseServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin
@RestController
@RequestMapping("/houses")
public class HouseController {

    @Autowired
    private HouseServiceImplementation houseServiceImplementation;

    //Create a method handler for home-page(index.html) that will display a list of houses
    @GetMapping("/all")
    public ResponseEntity<List<House>> getAllHouses(){
        return new ResponseEntity<>(houseServiceImplementation.getAllHouses(), HttpStatus.OK);
    }

    @GetMapping("/houses/{houseCode}")
    public ResponseEntity<House> getHouses(@PathVariable("houseCode") Integer houseCode){

        return new ResponseEntity<>(houseServiceImplementation.findHousesById(houseCode), HttpStatus.OK);
    }

    //Method handler for request addition of house files
    @PostMapping("/addNewHouse")
    public ResponseEntity<House> addHouse(@RequestBody House house){

        return new ResponseEntity<>(houseServiceImplementation.saveHouse(house),HttpStatus.CREATED);
    }

    //Method Handler for saveAddedHouse request
    @PutMapping("/editHouse/{houseCode}")
    public ResponseEntity editHouseForm(@PathVariable(name = "houseCode") Integer houseCode){
        houseServiceImplementation.getHouseByCode(houseCode);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteHouse/{houseCode}")
    public ResponseEntity deleteHouse(@PathVariable(name = "houseCode") Integer houseCode){
        houseServiceImplementation.deleteHouse(houseCode);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

//    //search functionality to read the keyword from the parameter URL
//    @GetMapping("/search")
//    public String searchHouse(Model model, @Param("keyword") String keyword){
//        List<House> houseList = houseServiceImplementation.listAll(keyword);
//        model.addAttribute("houseList", houseList);
//        model.addAttribute("keyword", keyword);
//
//        return "index";
//
//    }

    //pagination method
//    @GetMapping()
//    public ResponseEntity<List<House>> getAllHouses(@RequestParam(defaultValue = "0") Integer pageNo,
//                                                    @RequestParam(defaultValue = "4") Integer pageSize,
//                                                    @RequestParam(defaultValue = "id") String sortBy)
//    {
//        List<House> houseList = houseServiceImplementation.listAll(pageNo, pageSize, sortBy);
//
//        return new ResponseEntity<List<House>>(houseList, new HttpHeaders(),HttpStatus.OK);
//    }


}
