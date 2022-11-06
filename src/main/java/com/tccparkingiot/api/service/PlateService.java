package com.tccparkingiot.api.service;

import com.tccparkingiot.api.model.Plate;
import com.tccparkingiot.api.repository.PlateRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlateService {

    @Autowired
    public PlateRepository plateRepository;

    public List<Plate> listAll(){
        return plateRepository.findAll();
    }

    public Plate findByNameOrSave(String plateNumber){

        Optional<Plate> plate = plateRepository.findByplateNumber(plateNumber);
        return plate.orElseGet(() -> plateRepository.save(new Plate(plateNumber)));
    }
}
