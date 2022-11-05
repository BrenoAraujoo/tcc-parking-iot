package com.tccparkingiot.api.service;


import com.tccparkingiot.api.model.ParkingRental;
import com.tccparkingiot.api.repository.ParkingRentalRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingRentalService {

    @Autowired
    private ParkingRentalRepository parkingRentalRepository;

    public List<ParkingRental> listar() {
        List<ParkingRental> list = parkingRentalRepository.findAll();
        for (ParkingRental p : list) {
            if (p.getEndDate() != null) {
                p.setHoras(totalHours(p));

            }
        }
        return list;
    }

    public Integer totalHours(ParkingRental parkingRental) {
        int startDate = parkingRental.getStartDate().getHour();
        int endDate = parkingRental.getEndDate().getHour();

             return endDate - startDate;
    }
}
