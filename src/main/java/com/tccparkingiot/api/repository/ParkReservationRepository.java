package com.tccparkingiot.api.repository;

import com.tccparkingiot.api.model.ParkReservation;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ParkReservationRepository extends JpaRepository<ParkReservation, Long> {

//    @Query("select t from ParkReservation t where plateNumber = :plateNumber order by t.id desc")
//    List<ParkReservation> findLastResult(String plateNumber);


//    ParkReservation findFirstByplateNumberOrderByIdDesc(String plateNumber);
//        ParkReservation findFirstByplateNumberOrderByIdDesc(String plateNumber);
//        List<ParkReservation> findByplateNumberOrderByIdDesc(String plateNumber);

        @Query(value = "Select t from ParkReservation t where t.feeCharged = false and t.plateNumber = :plateNumber order by id asc")
        List <ParkReservation> getFirstResult(String plateNumber);



        @Transactional
        Long deleteByplateNumber(String plateNumber);

}
