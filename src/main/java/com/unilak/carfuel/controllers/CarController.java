/**
 * Names & Registration Numbers:
 * - Eminou Abdou : 26502/2024
 * - Bayambaze Jean Fred : 26398/2024
 * - Maaz montasir osman : 26812/2024
 */
package com.unilak.carfuel.controllers;

import com.unilak.carfuel.dto.CarRequest;
import com.unilak.carfuel.dto.FuelRequest;
import com.unilak.carfuel.dto.FuelStatsResponse;
import com.unilak.carfuel.models.Car;
import com.unilak.carfuel.models.FuelEntry;
import com.unilak.carfuel.services.CarFuelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarFuelService service;

    public CarController(CarFuelService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody CarRequest request) {
        return new ResponseEntity<>(service.createCar(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        return ResponseEntity.ok(service.getAllCars());
    }

    @PostMapping("/{id}/fuel")
    public ResponseEntity<FuelEntry> addFuelEntry(@PathVariable Long id, @RequestBody FuelRequest request) {
        return new ResponseEntity<>(service.addFuelEntry(id, request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/fuel/stats")
    public ResponseEntity<FuelStatsResponse> getFuelStats(@PathVariable Long id) {
        return ResponseEntity.ok(service.getFuelStats(id));
    }
}