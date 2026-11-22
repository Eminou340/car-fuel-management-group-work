/**
 * Names & Registration Numbers:
 * - Eminou Abdou : 26502/2024
 * - Bayambaze Jean Fred : 26398/2024
 * - Maaz montasir osman : 26812/2024
 */
package com.unilak.carfuel.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "fuel_entries")
public class FuelEntry extends BaseEntity {

    @Column(nullable = false)
    private Double liters;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Double odometer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    @JsonIgnore
    private Car car;

    public FuelEntry() {
        super();
    }

    public FuelEntry(Double liters, Double price, Double odometer, Car car) {
        super();
        this.liters = liters;
        this.price = price;
        this.odometer = odometer;
        this.car = car;
    }

    public Double getLiters() { return liters; }
    public void setLiters(Double liters) { this.liters = liters; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Double getOdometer() { return odometer; }
    public void setOdometer(Double odometer) { this.odometer = odometer; }

    public Car getCar() { return car; }
    public void setCar(Car car) { this.car = car; }

    @Override
    public String toString() {
        return "FuelEntry{" + "id=" + getId() + ", liters=" + liters + ", price=" + price + ", odometer=" + odometer + '}';
    }
}