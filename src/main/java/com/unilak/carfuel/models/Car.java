/**
 * Names & Registration Numbers:
 * - Eminou Abdou : 26502/2024
 * - Bayambaze Jean Fred : 26398/2024
 * - Maaz montasir osman : 26812/2024
 */
package com.unilak.carfuel.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cars")
public class Car extends BaseEntity {

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(name = "car_year", nullable = false)
    private Integer year;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FuelEntry> fuelEntries = new ArrayList<>();

    public Car() {
        super();
    }

    public Car(String brand, String model, Integer year) {
        super();
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }

    public List<FuelEntry> getFuelEntries() { return fuelEntries; }
    public void setFuelEntries(List<FuelEntry> fuelEntries) { this.fuelEntries = fuelEntries; }

    @Override
    public String toString() {
        return "Car{" + "id=" + getId() + ", brand='" + brand + '\'' + ", model='" + model + '\'' + ", year=" + year + '}';
    }
}