package com.tccparkingiot.api.service;

import com.tccparkingiot.api.exceptions.EntityNotFoundException;
import com.tccparkingiot.api.model.ParkingSpot;
import com.tccparkingiot.api.repository.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingSpotService {

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    public ParkingSpot findOrFail(Long id){
        return parkingSpotRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(
                        String.format("Parking spot with id %d not found",id)
                ));
    }

    public ParkingSpot changeAvailability(Long id){
        var parkingSpot = findOrFail(id);
        if(parkingSpot.getAvailable()){
            parkingSpot.setAvailable(false);
            return parkingSpotRepository.save(parkingSpot);
        }else{
            parkingSpot.setAvailable(true);
        }

            return parkingSpotRepository.save(parkingSpot);
    }

}
