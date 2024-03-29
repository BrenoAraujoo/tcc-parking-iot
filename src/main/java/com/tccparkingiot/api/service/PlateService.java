package com.tccparkingiot.api.service;

import com.tccparkingiot.api.exceptions.EntityInUseException;
import com.tccparkingiot.api.exceptions.EntityNotFoundException;
import com.tccparkingiot.api.model.Plate;
import com.tccparkingiot.api.model.User;
import com.tccparkingiot.api.repository.PlateRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PlateService {

    public static final String MSG_PLATE_NOT_FOUND = "Placa  com id %d não encontrada";
    @Autowired
    public PlateRepository plateRepository;

    public List<Plate> listAll(){
        return plateRepository.findAll();
    }

    public Plate save(Plate plate){
        return plateRepository.save(plate);
    }

    public void delete(Long id){
        try {
            plateRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new EntityInUseException(
                    String.format("Placa com id %d está em uso, não pode ser removida",id)
            );
        }catch (EmptyResultDataAccessException e){
            throw new EntityNotFoundException(
                    String.format(MSG_PLATE_NOT_FOUND,id)
            );
        }
    }
    public Plate findByNameOrSave(String plateNumber){

        Optional<Plate> plate = plateRepository.findByplateNumber(plateNumber);
        return plate.orElseGet(() -> plateRepository.save(Plate.builder().plateNumber(plateNumber).build()));
    }

    public Plate findOrFail(Long id){
        return plateRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(
                        String.format(MSG_PLATE_NOT_FOUND,id)
                ));
    }

    public Plate findByplateNumber(String plateNumber){
        return plateRepository.findByplateNumber(plateNumber)
                .orElseThrow(()-> new EntityNotFoundException(
                        String.format("Plate %s not found",plateNumber)
                ));
    }
}
