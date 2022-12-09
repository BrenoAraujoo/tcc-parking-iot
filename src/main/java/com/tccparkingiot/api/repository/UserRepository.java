package com.tccparkingiot.api.repository;

import com.tccparkingiot.api.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByPlateId(Long Id);
    Optional<User> findByPlatePlateNumber(String plateNumber);

}
