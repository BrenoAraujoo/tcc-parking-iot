package com.tccparkingiot.api.controller;

import com.tccparkingiot.api.model.ParkingRental;
import com.tccparkingiot.api.repository.ParkReservationRepository;
import com.tccparkingiot.api.repository.ParkingRentalRepository;
import com.tccparkingiot.api.repository.PlateRepository;
import com.tccparkingiot.api.service.ParkReservationService;
import com.tccparkingiot.api.service.ParkingRentalService;
import com.tccparkingiot.api.service.PlateService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parking-rentals")
public class ParkingRentalController {

    @Autowired
    private ParkingRentalRepository parkingRentalRepository;

    @Autowired
    private ParkingRentalService parkingRentalService;

    @Autowired
    private PlateService plateService;

    @Autowired
    private PlateRepository plateRepository;

    @Autowired
    private ParkReservationRepository parkReservationRepository;

    @Autowired
    ParkReservationService parkReservationService;

    @GetMapping
    List<ParkingRental> listAll(){
        return  parkingRentalService.listAll();
    }

    @GetMapping("/{id}")
    public ParkingRental findById(@PathVariable Long id){
        return parkingRentalService.findById(id);
    }

    @GetMapping("/findByPlateId")
    public ParkingRental findByPlateId(Long id){
        return parkingRentalRepository.findByPlateId(id);
    }
    @GetMapping("/findByPlateNumber")
    public List<ParkingRental> findByPlateNumber(String plateNumber){
        return parkingRentalService.findByPlateNumber(plateNumber);
    }

    @PostMapping("/{parkingSpotId}/{plateNumber}")
    public ParkingRental save(@RequestBody ParkingRental parkingRental, @PathVariable Long parkingSpotId, @PathVariable String plateNumber){


        var plate = plateService.findByNameOrSave(plateNumber);
        parkingRental.setPlate(plate);

        var parkReservation = parkReservationService.getFirstResult(plateNumber);
        parkingRental.setStartDate(parkReservation.getStartDate());//Copy start date from parkReservation

        parkReservation.setFeeCharged(true); // Changes fee charged and saves

//        parkReservationRepository.deleteById(parkReservation.getId());
        parkReservationRepository.deleteByplateNumber(plateNumber);
        return parkingRentalService.save(parkingRental, parkingSpotId);
    }






    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        parkingRentalService.delete(id);
    }

}
