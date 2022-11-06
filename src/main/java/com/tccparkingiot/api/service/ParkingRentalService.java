package com.tccparkingiot.api.service;


import com.tccparkingiot.api.exceptions.EntityInUseException;
import com.tccparkingiot.api.exceptions.EntityNotFoundException;
import com.tccparkingiot.api.model.ParkingRental;
import com.tccparkingiot.api.repository.ParkingRentalRepository;
import com.tccparkingiot.api.repository.PlateRepository;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingRentalService {


    public static final String MSG_PARKING_RENTAL_NOT_FOUND = "Parking rental with id %d not found";
    @Autowired
    private ParkingRentalRepository parkingRentalRepository;
    @Autowired
    private PlateRepository plateRepository;
    @Autowired
    private PlateService plateService;



    public List<ParkingRental> listAll() {
        List<ParkingRental> list = parkingRentalRepository.findAll();
        for (ParkingRental p : list) {
            if (p.getEndDate() != null){
                p.setHour(getTotalHours(p));
                p.setValue(calculateTotalValue(p.getValue(),p.getHour()));
            }else {
                p.setHour(0);
            }

        }
        return list;
    }
    public ParkingRental findById(Long id){
        ParkingRental rental = parkingRentalRepository.findById(id).get();
        if(rental.getEndDate() != null){
            Integer totalHour = getTotalHours(rental);
            rental.setHour(totalHour);
            rental.setValue(calculateTotalValue(rental.getValue(), totalHour));
            return rental;
        }
        return rental;
    }

    public ParkingRental save(ParkingRental parkingRental){
        var plateNumber = parkingRental.getPlate().getPlateNumber();
        var plate = plateService.findByNameOrSave(plateNumber);
        parkingRental.setPlate(plate);
        return parkingRentalRepository.save(parkingRental);
    }

    public Integer getTotalHours(ParkingRental parkingRental) {
        int startHour = parkingRental.getStartDate().getHour();
        int endHour = parkingRental.getEndDate().getHour();

             return endHour - startHour;
    }

    public Double calculateTotalValue(Double value, Integer hour){
        return hour * value;
    }

    public ParkingRental findOrFail(Long id){
        return parkingRentalRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(
                String.format(MSG_PARKING_RENTAL_NOT_FOUND, id)
        ));
    }
}
