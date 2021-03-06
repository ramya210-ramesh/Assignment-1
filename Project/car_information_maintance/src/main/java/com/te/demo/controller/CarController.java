package com.te.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.te.demo.bean.AdminDetails;
import com.te.demo.bean.CarDetails;
import com.te.demo.jwt.JwtUtil;
import com.te.demo.model.AdminResponse;
import com.te.demo.model.AuthenticationRequest;
import com.te.demo.service.CarService;

@RestController
@CrossOrigin(origins = "*")

public class CarController {

	@Autowired
	private CarService service;
	@Autowired
	private AuthenticationManager authenticateManager;
	@Autowired
	private JwtUtil jwtTokenUtil;

	@PostMapping("/adminRegister")
	public ResponseEntity<AdminResponse> createRegistration(@RequestBody AdminDetails admin) {
		AdminDetails createRegistration = service.createRegistration(admin);
		if (createRegistration != null) {

			return ResponseEntity.ok(new AdminResponse(false, "Success", null));
		} else {
			return ResponseEntity.badRequest().body(new AdminResponse(true, "Username Already Exists ", null));
		}
	}

	@PostMapping("/adminlogin") // ResponseEntity<?>
	public ResponseEntity<AdminResponse> createAuthenticationToken(
			@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

		try {
			authenticateManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
			UserDetails userDetails = service.loadUserByUsername(authenticationRequest.getUsername());

			String token = jwtTokenUtil.generateToken(userDetails);

			return ResponseEntity.ok().body(new AdminResponse(false, "Logged in sucessfully!", token));

		} catch (BadCredentialsException e) {
			return ResponseEntity.badRequest().body(new AdminResponse(true, "Data was not found!", null));
		}

	}

	@GetMapping( "/seeall")
	public ResponseEntity<?> cardetails() {
		try {
			List<CarDetails> carDetails = service.CarDetails();
			return new ResponseEntity<List<CarDetails>>(carDetails, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Something Went Wrong !", HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

//	@GetMapping(path = { "/seeall" })
//	public List<CarDetails> carDetails(CarDetails carDetails) {
//		List<CarDetails> details = service.CarDetails();
//		return details;
//	}

	@PostMapping("/addCar")
	public ResponseEntity<?> addDetails(@RequestBody CarDetails car) {
		try {
			CarDetails addCars = service.addCar(car);
			return new ResponseEntity<String>("Data is Inserted !", HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<String>("Something Went Wrong !", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/deleteCar/{carId}")
	public ResponseEntity<?> deleteDetails(@PathVariable int carId) {
		try {
			service.deleteCar(carId);
			return new ResponseEntity<String>("Car Details is Deleted", HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<String>("Something went Wrong ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping( "/updateCar/{carId}")
	public ResponseEntity<?> updateDetails(@PathVariable int carId, @RequestBody CarDetails car) {
		try {
			service.updateCar(carId, car);
			return new ResponseEntity<String>("car Details Upadated", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
