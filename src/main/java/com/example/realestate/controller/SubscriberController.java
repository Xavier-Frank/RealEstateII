package com.example.realestate.controller;

import com.example.realestate.dao.SubscriberRepo;
import com.example.realestate.model.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subscribers")
public class SubscriberController {
    @Autowired
    private SubscriberRepo subscriberRepo;

    @GetMapping
    public List<Subscriber> findAllSubscribers(){
        return subscriberRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subscriber> findSubscriberById(@PathVariable(value = "id") long id) {
        Optional<Subscriber> subscriber = subscriberRepo.findById(id);
        if(subscriber.isPresent()){
            return ResponseEntity.ok().body(subscriber.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

}
