package com.tccparkingiot.api.repository;

import com.tccparkingiot.api.model.ParkingSpot;
import com.tccparkingiot.api.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot,Long> {

    List<ParkingSpot> findByPlatePlateNumber(String plateNumber);
}
