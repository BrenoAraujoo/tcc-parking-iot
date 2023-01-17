package com.tccparkingiot.api.controller;

import com.tccparkingiot.api.model.User;
import com.tccparkingiot.api.repository.UserRepository;
import com.tccparkingiot.api.service.UserService;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
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
    public List<User> listAll(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id){
        return userService.findOrFail(id);
    }

    @GetMapping("/find-by-plate/{plateNumber}")
    Optional<User> findByPlate(@PathVariable String plateNumber){
        return userRepository.findByPlatePlateNumber(plateNumber);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody @Valid User user){
        return userService.save(user);
    }

    @PutMapping("/{id}")
    public User update(@RequestBody User user, @PathVariable Long id){
        var actualUser = userService.findOrFail(id);
        BeanUtils.copyProperties(user,actualUser,"id");
        return userService.save(actualUser);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
         userService.delete(id);
    }
}
