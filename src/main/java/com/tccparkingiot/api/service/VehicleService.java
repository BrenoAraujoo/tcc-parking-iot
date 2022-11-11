package com.tccparkingiot.api.service;


import com.tccparkingiot.api.exceptions.EntityInUseException;
import com.tccparkingiot.api.exceptions.EntityNotFoundException;
import com.tccparkingiot.api.exceptions.VehicleAlreadyExistsException;
import com.tccparkingiot.api.model.Vehicle;
import com.tccparkingiot.api.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
    private static final String MSG_VEHICLE_NOT_FOUND = "Vehicle with id %d not found";

    @Autowired
    private VehicleRepository vehicleRepository;

    public Vehicle save(Vehicle vehicle) {
        try {
            vehicleRepository.save(vehicle);
            return vehicle;
        } catch (DataIntegrityViolationException e) {
            throw new VehicleAlreadyExistsException(
                    String.format("Vehicle with plate %s is already registered", vehicle.getPlate()));
        }
    }

    public void remove(Long id) {
        try {
            vehicleRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Vehicle with id %d in use", id)
            );
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(
                    String.format(MSG_VEHICLE_NOT_FOUND, id));
        }
    }

    public Vehicle findOrFail(Long id) {
        return vehicleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                String.format(MSG_VEHICLE_NOT_FOUND, id)));

    }

    public Vehicle findByPlate(String plateNumber){
            var vehicle = vehicleRepository.findByPlatePlateNumber(plateNumber);
            return vehicle;
        }
    }

