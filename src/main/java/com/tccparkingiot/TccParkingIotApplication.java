package com.tccparkingiot;

import com.tccparkingiot.api.model.ParkingRental;
import com.tccparkingiot.api.service.ParkingRentalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TccParkingIotApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TccParkingIotApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


	}
}
