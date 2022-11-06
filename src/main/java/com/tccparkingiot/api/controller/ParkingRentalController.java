package com.tccparkingiot.api.controller;

import com.tccparkingiot.api.model.ParkingRental;
import com.tccparkingiot.api.repository.ParkingRentalRepository;
import com.tccparkingiot.api.service.ParkingRentalService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parking-rentals")
public class ParkingRentalController {

    @Autowired
    private ParkingRentalRepository parkingRentalRepository;

    @Autowired
    private ParkingRentalService parkingRentalService;

    @GetMapping
    List<ParkingRental> listAll(){
        return  parkingRentalService.listAll();
    }

    @GetMapping("/{id}")
    public ParkingRental findById(@PathVariable Long id){
        return parkingRentalService.findById(id);
    }

    @PostMapping
    public ParkingRental save(@RequestBody ParkingRental parkingRental){
        return parkingRentalService.save(parkingRental);
    }
}
