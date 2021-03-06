package com.example.realestate.dao;

import com.example.realestate.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Long> {

    @Query( value = "SELECT * FROM bookings u WHERE u.house_id = ?", nativeQuery = true)
    List<Booking> findAllByHouseId(long id);
}
