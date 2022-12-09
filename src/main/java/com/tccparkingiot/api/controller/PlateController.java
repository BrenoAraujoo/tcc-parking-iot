package com.tccparkingiot.api.controller;


import com.tccparkingiot.api.model.Plate;
import com.tccparkingiot.api.repository.PlateRepository;
import com.tccparkingiot.api.service.PlateService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plates")
public class PlateController {

    @Autowired
    private PlateService plateService;
    @Autowired
    private PlateRepository plateRepository;

    @GetMapping
    public List<Plate> listAll(){
        return plateService.listAll();
    }

    @GetMapping("/{id}")
    public Plate findById(@PathVariable Long id){
        return plateService.findOrFail(id);
    }

    @GetMapping("/find-by-plate")
    public Plate findByPlateNumber(String plateNumber){
        return plateService.findByplateNumber(plateNumber);
    }


    @GetMapping("/find-by-name-or-save")
    public Plate findByNameOrSave(String plateNumber){
        return plateService.findByNameOrSave(plateNumber);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Plate save(@RequestBody @Valid Plate plate){
        return plateService.save(plate);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        plateService.delete(id);
    }

    @PutMapping("/{id}")
    public Plate update(@RequestBody Plate plate, @PathVariable Long id){
        var actualPlate = plateService.findOrFail(id);
        BeanUtils.copyProperties(plate,actualPlate, "id");
        return plateRepository.save(actualPlate);
    }
}
