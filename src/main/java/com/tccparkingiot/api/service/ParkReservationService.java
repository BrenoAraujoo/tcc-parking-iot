package com.tccparkingiot.api.service;


import com.tccparkingiot.api.model.ParkReservation;
import com.tccparkingiot.api.repository.ParkReservationRepository;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkReservationService {
    @Autowired
    private ParkReservationRepository repository;


    public ParkReservation getFirstResult(String plateNumber){

        var firstResult = repository.getFirstResult(plateNumber);
        return firstResult
                .stream()
                .limit(1)
                .collect(Collectors.toList())
                .get(0);
    }

    public ParkReservation setFeeChargedToTrue(Long id){
        ParkReservation parkReservation = repository.findById(id).get();
        parkReservation.setFeeCharged(true);
        return repository.save(parkReservation);
    }


}
