package com.tccparkingiot.api.controller;

import com.tccparkingiot.api.exceptions.EntityNotFoundException;
import com.tccparkingiot.api.model.ParkReservation;
import com.tccparkingiot.api.repository.ParkReservationRepository;
import com.tccparkingiot.api.repository.ParkingSpotRepository;
import com.tccparkingiot.api.service.ParkReservationService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/park-reservation")
public class ParkReservationController {

    @Autowired
    private ParkReservationRepository parkReservationRepository;

    @Autowired
    private ParkReservationService parkReservationService;

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @GetMapping
    private List<ParkReservation> findAll() {
        return parkReservationRepository.findAll();
    }

    @GetMapping("/{id}")
    private Optional<ParkReservation> findById(@PathVariable Long id) {
        return parkReservationRepository.findById(id);
    }


//    @GetMapping("/find-last/{plateNumber}")
//    private ParkReservation findByPlateNumberOrderByIdDesc(@PathVariable String plateNumber){
//        return parkReservationRepository.findFirstByplateNumberOrderByIdDesc(plateNumber);
//    }

//
//    @GetMapping("/find-last/{plateNumber}")
//    private List<ParkReservation> findByPlateNumberOrderByIdDesc(@PathVariable String plateNumber){
//        return parkReservationRepository.findByplateNumberOrderByIdDesc(plateNumber);
//    }


    @GetMapping("/find-first/{plateNumber}")
    private ParkReservation findByPlateNumberOrderByIdDesc(@PathVariable String plateNumber) {

        try {
            return parkReservationService.getFirstResult(plateNumber);
        } catch (IndexOutOfBoundsException e) {
            throw new EntityNotFoundException(String.format("Placa %s não encontrada", plateNumber));
        }

    }




    @PostMapping
    private ParkReservation save(@RequestBody ParkReservation parkReservation) {

//        var parkingSpotId = parkReservation.getParkingSpot().getId();
//        var actualParkingSpot = parkingSpotRepository.findById(parkingSpotId).get();
//        parkReservation.setParkingSpot(actualParkingSpot);

        return parkReservationRepository.save(parkReservation);
    }
}
