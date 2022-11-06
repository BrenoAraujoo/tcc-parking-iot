package com.tccparkingiot.api.controller;


import com.tccparkingiot.api.model.Plate;
import com.tccparkingiot.api.repository.PlateRepository;
import com.tccparkingiot.api.service.PlateService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/plates")
public class PlateController {

    @Autowired
    private PlateService plateService;
    @Autowired
    private PlateRepository plateRepository;

    @GetMapping
    List<Plate> listAll(){
        return plateService.listAll();
    }

    @GetMapping("/findByPlateNumber")
    Optional<Plate> findByPlateNumber(String plateNumber){
        return plateRepository.findByplateNumber(plateNumber);
    }


    @GetMapping("/findByNameOrSave")
    public Plate findByNameOrSave(String plateNumber){
        return plateService.findByNameOrSave(plateNumber);
    }
}
