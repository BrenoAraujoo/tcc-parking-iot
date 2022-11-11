package com.tccparkingiot.api.controller;

import com.tccparkingiot.api.model.User;
import com.tccparkingiot.api.repository.UserRepository;
import com.tccparkingiot.api.service.UserService;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;
    @GetMapping
    public List<User> users(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id){
        return userService.findOrFail(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody User user){
        return userService.save(user);
    }

    @PutMapping("/{id}")
    public User update(@RequestBody User user, @PathVariable Long id){
        var actualUser = userService.findOrFail(id);
        BeanUtils.copyProperties(user,actualUser,"id","vehicles");
        return userService.save(actualUser);
    }
}
