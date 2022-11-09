package com.tccparkingiot.api.repository;

import com.tccparkingiot.api.model.ParkingRental;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRentalRepository extends JpaRepository<ParkingRental, Long> {

    ParkingRental findByPlateId(Long id);
    List<ParkingRental> findByPlatePlateNumber(String plateNumber);
}
