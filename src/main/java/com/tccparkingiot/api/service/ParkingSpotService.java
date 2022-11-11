package com.tccparkingiot.api.service;

import com.tccparkingiot.api.exceptions.EntityNotFoundException;
import com.tccparkingiot.api.model.ParkingSpot;
import com.tccparkingiot.api.model.Plate;
import com.tccparkingiot.api.model.Vehicle;
import com.tccparkingiot.api.repository.ParkingSpotRepository;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
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

    public ParkingSpot findByPlate(String plateNumber){
        var parkingSpot = parkingSpotRepository.findByPlatePlateNumber(plateNumber);
        var count = (long) parkingSpot.size();
        if(count != 0){
        var last = parkingSpot
                .stream()
                .filter(p -> p.getPlate().getPlateNumber().equals(plateNumber))
                .skip(count-1).findFirst()
                .orElseThrow(()->new NoSuchElementException());
        return last;

        }
        return null;
    }


    public ParkingSpot setParkingSpotAvailable(Long id){
        var parkingSpot = findOrFail(id);
            parkingSpot.setAvailable(true);
            parkingSpot.setPlate(null);
            return parkingSpotRepository.save(parkingSpot);
    }

    public ParkingSpot setParkingSpotPlate(ParkingSpot parkingSpot, Plate plate){
        parkingSpot.setPlate(plate);
       return  parkingSpotRepository.save(parkingSpot);
    }

    public Long getAvailableParkingSpots(){
        return parkingSpotRepository
                .findAll()
                .stream()
                .filter(ParkingSpot::getAvailable)
                .count();
    }


}
