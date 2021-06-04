package com.example.realestate.dao;


import com.example.realestate.model.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HouseRepo extends JpaRepository <House, Long> {
    @Query(value = "SELECT * FROM houses u WHERE u.agent_id =?", nativeQuery = true)
    List<House> findAllByAgent(long id);

    @Query(value = "SELECT * FROM houses u WHERE u.id = ? AND u.agent_id =? ", nativeQuery = true)
    ResponseEntity<House> findHouseByAgentId(long house_id, long agent_id);

    @Query(value = "SELECT * FROM houses u WHERE DATEDIFF(NOW(),u.created_at) < 7", nativeQuery = true)
    List<House> findRecent();

    @Query(value = "SELECT * FROM houses u WHERE u.booked = 0", nativeQuery = true)
    List<House> findAllUnBooked();

    @Query(value = "UPDATE houses SET slots = houses.slots + ? WHERE id = ?", nativeQuery = true)
    void bookHouse(int slots, long id);

//    //Search functionality
//    @Query(value = "SELECT * FROM houses h WHERE CONCAT(h.name,' ',h.description,' ',h.type,' ',h.capacity_unit, ' ' ,h.capacity, '' ,h.price) LIKE %?%")
//    public List<House> search(String keyword);

}
