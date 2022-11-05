package com.tccparkingiot.api.service;


import com.tccparkingiot.api.model.ParkingRental;
import com.tccparkingiot.api.repository.ParkingRentalRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingRentalService {


    @Autowired
    private ParkingRentalRepository parkingRentalRepository;

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
    public ParkingRental findOne (Long id){
        ParkingRental rental = parkingRentalRepository.findById(id).get();
        if(rental.getEndDate() != null){
            Integer totalHour = getTotalHours(rental);
            rental.setHour(totalHour);
            rental.setValue(calculateTotalValue(rental.getValue(), totalHour));
            return rental;
        }
        return rental;
    }

    public Integer getTotalHours(ParkingRental parkingRental) {
        int startHour = parkingRental.getStartDate().getHour();
        int endHour = parkingRental.getEndDate().getHour();

             return endHour - startHour;
    }

    public Double calculateTotalValue(Double value, Integer hour){
        return hour * value;
    }
}
