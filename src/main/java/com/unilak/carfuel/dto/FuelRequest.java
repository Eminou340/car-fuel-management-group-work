/**
 * Names & Registration Numbers:
 * - Eminou Abdou : 26502/2024
 * - Bayambaze Jean Fred : 26398/2024
 * - Maaz montasir osman : 26812/2024
 */
package com.unilak.carfuel.dto;

public class FuelRequest {
    private Double liters;
    private Double price;
    private Double odometer;

    public FuelRequest() {}

    public FuelRequest(Double liters, Double price, Double odometer) {
        this.liters = liters;
        this.price = price;
        this.odometer = odometer;
    }

    public Double getLiters() { return liters; }
    public void setLiters(Double liters) { this.liters = liters; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Double getOdometer() { return odometer; }
    public void setOdometer(Double odometer) { this.odometer = odometer; }

    @Override
    public String toString() {
        return "FuelRequest{" + "liters=" + liters + ", price=" + price + ", odometer=" + odometer + '}';
    }
}