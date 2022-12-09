package com.tccparkingiot.api.service;


import com.tccparkingiot.api.exceptions.EntityNotFoundException;
import com.tccparkingiot.api.model.ParkingRental;
import com.tccparkingiot.api.repository.ParkingRentalRepository;
import com.tccparkingiot.api.repository.PlateRepository;
import com.tccparkingiot.api.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ParkingRentalService {

    public static final String MSG_PARKING_RENTAL_NOT_FOUND = "Parking rental with id %d not found";
    @Autowired
    private ParkingRentalRepository parkingRentalRepository;
    @Autowired
    private PlateRepository plateRepository;
    @Autowired
    private PlateService plateService;

    @Autowired
    private ParkingSpotService parkingSpotService;

    @Autowired
    private UserRepository userRepository;


    public List<ParkingRental> listAll() {
     return parkingRentalRepository.findAll();
    }

    public ParkingRental findById(Long id){
        return parkingRentalRepository.findById(id).orElseThrow(()->new EntityNotFoundException(
                String.format(MSG_PARKING_RENTAL_NOT_FOUND,id)));
    }

    public List<ParkingRental> findByPlateNumber(String plateNumber){
        return parkingRentalRepository.findByPlatePlateNumber(plateNumber);

    }

    public void delete(Long id){
        try {
            parkingRentalRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new EntityNotFoundException(
                    String.format(MSG_PARKING_RENTAL_NOT_FOUND,id)
            );
        }
    }


    public ParkingRental save(ParkingRental parkingRental, Long parkingSpotId){
        var parkingSpot = parkingSpotService.findOrFail(parkingSpotId);

        var plateNumber = parkingRental.getPlate().getPlateNumber();
        var plate = plateService.findByNameOrSave(plateNumber);

        parkingRental.setPlate(plate);

        parkingSpot.setAvailable(false);

        var user = userRepository.findByPlatePlateNumber(plateNumber);
        parkingRental.setIsRegistered(user.isPresent());

        parkingRental.setParkingSpot(parkingSpot);// Set the parking spot on parking rental

        return parkingRentalRepository.save(parkingRental);

    }

    public ParkingRental findOrFail(Long id){
        return parkingRentalRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(
                String.format(MSG_PARKING_RENTAL_NOT_FOUND, id)
        ));
    }
}
