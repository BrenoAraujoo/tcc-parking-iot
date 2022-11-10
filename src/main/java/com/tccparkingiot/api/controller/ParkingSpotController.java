package com.tccparkingiot.api.controller;

import com.tccparkingiot.api.model.ParkingSpot;
import com.tccparkingiot.api.repository.ParkingSpotRepository;
import com.tccparkingiot.api.service.ParkingSpotService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parking-spots")
public class ParkingSpotController {

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @Autowired
    private ParkingSpotService parkingSpotService;

    @GetMapping
    public List<ParkingSpot> listAll(){
        return parkingSpotRepository.findAll();
    }

    @GetMapping("/{id}")
    public ParkingSpot findById(@PathVariable Long id){
        return parkingSpotService.findOrFail(id);
    }

    @PostMapping
    public ParkingSpot save(@RequestBody ParkingSpot parkingSpot){
        return parkingSpotRepository.save(parkingSpot);
    }

    @PutMapping("/set-parking-spot-available/{id}")
    public ParkingSpot setParkingSpotAvailable(@PathVariable Long id){
        return parkingSpotService.setParkingSpotAvailable(id);
    }

}
