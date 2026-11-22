/**
 * Names & Registration Numbers:
 * - Eminou Abdou : 26502/2024
 * - Bayambaze Jean Fred : 26398/2024
 * - Maaz montasir osman : 26812/2024
 */
package com.unilak.carfuel.dto;

public class FuelStatsResponse {
    private Double totalFuel;
    private Double totalCost;
    private Double averageConsumptionPer100Km;

    public FuelStatsResponse() {}

    public FuelStatsResponse(Double totalFuel, Double totalCost, Double averageConsumptionPer100Km) {
        this.totalFuel = totalFuel;
        this.totalCost = totalCost;
        this.averageConsumptionPer100Km = averageConsumptionPer100Km;
    }

    public Double getTotalFuel() { return totalFuel; }
    public void setTotalFuel(Double totalFuel) { this.totalFuel = totalFuel; }

    public Double getTotalCost() { return totalCost; }
    public void setTotalCost(Double totalCost) { this.totalCost = totalCost; }

    public Double getAverageConsumptionPer100Km() { return averageConsumptionPer100Km; }
    public void setAverageConsumptionPer100Km(Double averageConsumptionPer100Km) { this.averageConsumptionPer100Km = averageConsumptionPer100Km; }

    @Override
    public String toString() {
        return "FuelStatsResponse{" + "totalFuel=" + totalFuel + ", totalCost=" + totalCost + ", averageConsumptionPer100Km=" + averageConsumptionPer100Km + '}';
    }
}