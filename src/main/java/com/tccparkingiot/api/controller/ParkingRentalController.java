package com.tccparkingiot.api.controller;

import com.tccparkingiot.api.model.ParkingRental;
import com.tccparkingiot.api.repository.ParkingRentalRepository;
import com.tccparkingiot.api.repository.PlateRepository;
import com.tccparkingiot.api.service.ParkingRentalService;
import com.tccparkingiot.api.service.PlateService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
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


    @GetMapping
    List<ParkingRental> listAll() {
        return parkingRentalService.listAll();
    }

    @GetMapping("/{id}")
    public ParkingRental findById(@PathVariable Long id) {
        return parkingRentalService.findById(id);
    }

    @GetMapping("/findByPlateId")
    public ParkingRental findByPlateId(Long id) {
        return parkingRentalRepository.findByPlateId(id);
    }

    @GetMapping("/findByPlateNumber")
    public List<ParkingRental> findByPlateNumber(String plateNumber) {
        System.out.println(plateNumber);
        List<ParkingRental> byPlateNumber = parkingRentalService.findByPlateNumber(plateNumber);
        return byPlateNumber;

    }

    @GetMapping("/find-by-plate-end-date-null")
    public List<ParkingRental> findByPlateEndDateIsNull(String plateNumber) {

        List<ParkingRental> list = parkingRentalService.findByPlateNumber(plateNumber);

            return list.stream()
                .filter(parkingRental -> parkingRental.getEndDate() == null)
                    .limit(1)
                .collect(Collectors.toList());

    }

    @PostMapping("/{parkingSpotId}/{plateNumber}")
    public ParkingRental save(@RequestBody ParkingRental parkingRental, @PathVariable Long parkingSpotId, @PathVariable String plateNumber) {


        var plate = plateService.findByNameOrSave(plateNumber);
        parkingRental.setPlate(plate);

//        parkReservationRepository.deleteById(parkReservation.getId());
        return parkingRentalService.save(parkingRental, parkingSpotId);
    }


    @PutMapping("/set-end-date/{id}")
    public ParkingRental setEndDate(@PathVariable Long id, @RequestBody ParkingRental parkingRental){
        var actualParkingRental = parkingRentalService.findOrFail(id);
        BeanUtils.copyProperties(parkingRental,actualParkingRental,"id","plate","parkingSpot", "startDate","isRegistered");
        return parkingRentalRepository.save(actualParkingRental);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        parkingRentalService.delete(id);
    }

}
