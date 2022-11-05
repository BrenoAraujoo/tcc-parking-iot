package com.tccparkingiot.api.repository;

import com.tccparkingiot.api.model.ParkingRental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRentalRepository extends JpaRepository<ParkingRental, Long> {


}
