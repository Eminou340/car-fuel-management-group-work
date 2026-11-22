/**
 * Names & Registration Numbers:
 * - Eminou Abdou : 26502/2024
 * - Bayambaze Jean Fred : 26398/2024
 * - Maaz montasir osman : 26812/2024
 */
package com.unilak.carfuel.dto;

public class CarRequest {
    private String brand;
    private String model;
    private Integer year;

    public CarRequest() {}

    public CarRequest(String brand, String model, Integer year) {
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

    @Override
    public String toString() {
        return "CarRequest{" + "brand='" + brand + '\'' + ", model='" + model + '\'' + ", year=" + year + '}';
    }
}