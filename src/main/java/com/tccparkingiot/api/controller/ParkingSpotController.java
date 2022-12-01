package com.tccparkingiot.api.controller;

import com.tccparkingiot.api.model.ParkingSpot;
import com.tccparkingiot.api.repository.ParkingSpotRepository;
import com.tccparkingiot.api.service.ParkingSpotService;
import com.tccparkingiot.api.service.PlateService;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parking-spots")
public class ParkingSpotController {

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @Autowired
    private ParkingSpotService parkingSpotService;

    @Autowired
    private PlateService plateService;

    @GetMapping
    public List<ParkingSpot> listAll() {
        return parkingSpotRepository.findAll();
    }

    @GetMapping("/{id}")
    public ParkingSpot findById(@PathVariable Long id) {
        return parkingSpotService.findOrFail(id);
    }

    @GetMapping("/find-by-plate")
    public ParkingSpot findByPlate(String plateNumber) {
        return parkingSpotService.findByPlate(plateNumber);
    }

    @GetMapping("/available-parkingspot")
    public Long getAvailableParkingSpots() {
        return parkingSpotService.getAvailableParkingSpots();
    }


    @PostMapping
    public ParkingSpot save(@RequestBody ParkingSpot parkingSpot) {
        return parkingSpotRepository.save(parkingSpot);
    }

    @PutMapping("/set-parking-spot-status/{id}")
    public ParkingSpot setParkingSpotAvailable(@PathVariable Long id) {
        return parkingSpotService.clearAndSave(id);
    }

    @PutMapping("/{id}")
    public ParkingSpot update(@PathVariable Long id, @RequestBody ParkingSpot parkingSpot) {

        var plate = plateService.findOrFail(parkingSpot.getPlate().getId());
        var actualParkingSpot = parkingSpotService.findOrFail(id);

        actualParkingSpot.setPlate(plate);

        BeanUtils.copyProperties(parkingSpot, actualParkingSpot, "id", "plate");

        parkingSpotService.save(actualParkingSpot);
        return actualParkingSpot;
    }

    @PutMapping("/ocuppy-parking-spot-and-save-plate/{id}")
    public ParkingSpot occupyParkingSpotAndSavePlate(@PathVariable Long id, @RequestBody ParkingSpot parkingSpot) {
        /*
        If plate doesn't exist, create a new one
         */
        var plate = plateService.findByNameOrSave(parkingSpot.getPlate().getPlateNumber());
        var actualParkingSpot = parkingSpotService.findOrFail(id);

        actualParkingSpot.setPlate(plate);
        actualParkingSpot.setAvailable(false);

        BeanUtils.copyProperties(parkingSpot, actualParkingSpot, "id", "plate","available");

        parkingSpotService.save(actualParkingSpot);
        return actualParkingSpot;
    }

}
