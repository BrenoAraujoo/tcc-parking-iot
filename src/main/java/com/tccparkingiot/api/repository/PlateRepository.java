package com.tccparkingiot.api.repository;

import com.tccparkingiot.api.model.Plate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlateRepository extends JpaRepository<Plate, Long> {
    Optional<Plate> findByplateNumber(String plateNumber);


}
