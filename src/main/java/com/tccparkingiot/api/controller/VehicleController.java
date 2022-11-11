package com.tccparkingiot.api.controller;

import com.tccparkingiot.api.model.Vehicle;
import com.tccparkingiot.api.repository.VehicleRepository;
import com.tccparkingiot.api.service.VehicleService;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public List<Vehicle> listAll(){
        return vehicleRepository.findAll();
    }

    @GetMapping("/{id}")
    public Vehicle findById(@PathVariable Long id){
        return vehicleService.findOrFail(id);
    }

    @GetMapping("/findByPlate")
    public Vehicle findByPlate(String plate){
        return vehicleService.findByPlate(plate);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vehicle save(@RequestBody Vehicle vehicle){
        return vehicleService.save(vehicle);
    }

    @PutMapping("/{id}")
    public Vehicle update(@PathVariable Long id, @RequestBody Vehicle vehicle){
        var currentVehicle = vehicleService.findOrFail(id);
        BeanUtils.copyProperties(vehicle,currentVehicle,"id");
        vehicleService.save(currentVehicle);

        return currentVehicle;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        vehicleService.remove(id);
    }
}
