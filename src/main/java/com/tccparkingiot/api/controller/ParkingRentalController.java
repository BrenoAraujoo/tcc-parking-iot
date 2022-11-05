package com.tccparkingiot.api.controller;

import com.tccparkingiot.api.model.ParkingRental;
import com.tccparkingiot.api.model.User;
import com.tccparkingiot.api.repository.ParkingRentalRepository;
import com.tccparkingiot.api.repository.UserRepository;
import com.tccparkingiot.api.service.ParkingRentalService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parking-rentals")
public class ParkingRentalController {

    @Autowired
    private ParkingRentalRepository parkingRentalRepository;

    @Autowired
    private ParkingRentalService parkingRentalService;

    @GetMapping
    List<ParkingRental> listar(){
        return  parkingRentalService.listar();
    }
}
