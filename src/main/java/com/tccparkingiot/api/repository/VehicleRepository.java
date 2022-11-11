package com.tccparkingiot.api.repository;

import com.tccparkingiot.api.model.Vehicle;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Long> {

    Vehicle findByPlatePlateNumber(String plate);
}
