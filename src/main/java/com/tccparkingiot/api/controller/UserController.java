package com.tccparkingiot.api.controller;
import com.tccparkingiot.api.model.User;
import com.tccparkingiot.api.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @GetMapping
    public List<User> users(){
        return userRepository.findAll();
    }
}
