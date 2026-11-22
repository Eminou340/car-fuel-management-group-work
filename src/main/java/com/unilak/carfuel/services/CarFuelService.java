/**
 * Names & Registration Numbers:
 * - Eminou Abdou : 26502/2024
 * - Bayambaze Jean Fred : 26398/2024
 * - Maaz montasir osman : 26812/2024
 */
package com.unilak.carfuel.services;

import com.unilak.carfuel.dto.CarRequest;
import com.unilak.carfuel.dto.FuelRequest;
import com.unilak.carfuel.dto.FuelStatsResponse;
import com.unilak.carfuel.exceptions.BadRequestException;
import com.unilak.carfuel.exceptions.ResourceNotFoundException;
import com.unilak.carfuel.models.Car;
import com.unilak.carfuel.models.FuelEntry;
import com.unilak.carfuel.repositories.CarRepository;
import com.unilak.carfuel.repositories.FuelEntryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarFuelService {

    private final CarRepository carRepository;
    private final FuelEntryRepository fuelEntryRepository;

    public CarFuelService(CarRepository carRepository, FuelEntryRepository fuelEntryRepository) {
        this.carRepository = carRepository;
        this.fuelEntryRepository = fuelEntryRepository;
    }

    public Car createCar(CarRequest request) {
        if (request.getBrand() == null || request.getModel() == null || request.getYear() == null) {
            throw new BadRequestException("All fields (brand, model, year) are mandatory.");
        }
        Car car = new Car(request.getBrand(), request.getModel(), request.getYear());
        return carRepository.save(car);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public FuelEntry addFuelEntry(Long carId, FuelRequest request) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with ID: " + carId));

        if (request.getLiters() == null || request.getPrice() == null || request.getOdometer() == null) {
            throw new BadRequestException("Liters, price, and odometer are required.");
        }
        if (request.getLiters() <= 0 || request.getPrice() <= 0 || request.getOdometer() < 0) {
            throw new BadRequestException("Values must be positive numbers.");
        }

        FuelEntry entry = new FuelEntry(request.getLiters(), request.getPrice(), request.getOdometer(), car);
        return fuelEntryRepository.save(entry);
    }

    public FuelStatsResponse getFuelStats(Long carId) {
        if (!carRepository.existsById(carId)) {
            throw new ResourceNotFoundException("Car not found with ID: " + carId);
        }

        List<FuelEntry> entries = fuelEntryRepository.findByCarIdOrderByOdometerAsc(carId);
        if (entries.isEmpty()) {
            return new FuelStatsResponse(0.0, 0.0, 0.0);
        }

        double totalFuel = 0.0;
        double totalCost = 0.0;
        for (FuelEntry entry : entries) {
            totalFuel += entry.getLiters();
            totalCost += entry.getPrice();
        }

        double distance;
        if (entries.size() > 1) {
            distance = entries.get(entries.size() - 1).getOdometer() - entries.get(0).getOdometer();
        } else {
            distance = entries.get(0).getOdometer();
        }

        double avgPer100Km = (distance > 0) ? (totalFuel / distance) * 100 : 0.0;

        return new FuelStatsResponse(
                Math.round(totalFuel * 100.0) / 100.0,
                Math.round(totalCost * 100.0) / 100.0,
                Math.round(avgPer100Km * 100.0) / 100.0
        );
    }
}