package com.tccparkingiot.api.service;

import com.tccparkingiot.api.exceptions.EntityInUseException;
import com.tccparkingiot.api.exceptions.EntityNotFoundException;
import com.tccparkingiot.api.model.User;
import com.tccparkingiot.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public static final String MSG_USER_NOT_FOUND = "User with id %d not found";
    @Autowired
    private UserRepository userRepository;

    public User save(User user){
        return userRepository.save(user);
    }

    public User findOrFail(Long id){
        return userRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(
                        String.format(MSG_USER_NOT_FOUND, id)
                ));
    }

    public void delete(Long id){
        try {
            userRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new EntityNotFoundException(
                    String.format(MSG_USER_NOT_FOUND, id)
            );
        }catch (DataIntegrityViolationException e){
            throw new EntityInUseException(
                    String.format("User with id %d in use, can't be removed",id)
            );
        }
    }

}
