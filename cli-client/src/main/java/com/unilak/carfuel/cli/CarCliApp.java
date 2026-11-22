/**
 * Names & Registration Numbers:
 * - Eminou Abdou : 26502/2024
 * - Bayambaze Jean Fred : 26398/2024
 * - Maaz montasir osman : 26812/2024
 */
package com.unilak.carfuel.cli;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarCliApp {

    private static final String BASE_URL = "http://localhost:8080/api/cars";
    private static final HttpClient client = HttpClient.newHttpClient();

    public static void main(String[] args) {
        System.out.println("=== Car Management & Fuel Tracking CLI ===");
        System.out.println("Commands:");
        System.out.println(" 1. create-car --brand <brand> --model <model> --year <year>");
        System.out.println(" 2. add-fuel --carId <id> --liters <liters> --price <price> --odometer <odometer>");
        System.out.println(" 3. fuel-stats --carId <id>");
        System.out.println(" 4. exit");
        System.out.println("------------------------------------------");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("\n> ");
            String line = scanner.nextLine().trim();

            if (line.equalsIgnoreCase("exit")) break;
            if (line.isEmpty()) continue;

            try {
                if (line.startsWith("create-car")) {
                    handleCreateCar(line);
                } else if (line.startsWith("add-fuel")) {
                    handleAddFuel(line);
                } else if (line.startsWith("fuel-stats")) {
                    handleFuelStats(line);
                } else {
                    System.out.println("Unknown command!");
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static void handleCreateCar(String input) throws Exception {
        Map<String, String> args = parseArguments(input);
        String brand = args.get("brand");
        String model = args.get("model");
        String year = args.get("year");

        if (brand == null || model == null || year == null) {
            System.out.println("Usage: create-car --brand <brand> --model <model> --year <year>");
            return;
        }

        String json = String.format("{\"brand\":\"%s\",\"model\":\"%s\",\"year\":%s}", brand, model, year);
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());
        if (res.statusCode() == 201) {
            System.out.println("Car registered successfully! " + res.body());
        } else {
            System.out.println("Failed: " + res.body());
        }
    }

    private static void handleAddFuel(String input) throws Exception {
        Map<String, String> args = parseArguments(input);
        String carId = args.get("carid");
        String liters = args.get("liters");
        String price = args.get("price");
        String odometer = args.get("odometer");

        if (carId == null || liters == null || price == null || odometer == null) {
            System.out.println("Usage: add-fuel --carId <id> --liters <liters> --price <price> --odometer <odometer>");
            return;
        }

        String json = String.format("{\"liters\":%s,\"price\":%s,\"odometer\":%s}", liters, price, odometer);
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + carId + "/fuel"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());
        if (res.statusCode() == 201) {
            System.out.println("Fuel added successfully! " + res.body());
        } else {
            System.out.println("Failed: " + res.body());
        }
    }

    private static void handleFuelStats(String input) throws Exception {
        Map<String, String> args = parseArguments(input);
        String carId = args.get("carid");

        if (carId == null) {
            System.out.println("Usage: fuel-stats --carId <id>");
            return;
        }

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + carId + "/fuel/stats"))
                .GET()
                .build();

        HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());
        if (res.statusCode() == 200) {
            String b = res.body();
            String totalFuel = extractValue(b, "totalFuel");
            String totalCost = extractValue(b, "totalCost");
            String avg = extractValue(b, "averageConsumptionPer100Km");

            System.out.println("Total fuel: " + totalFuel + " L");
            System.out.println("Total cost: " + totalCost);
            System.out.println("Average consumption: " + avg + " L/100km");
        } else {
            System.out.println("Error: " + res.body());
        }
    }

    private static Map<String, String> parseArguments(String input) {
        Map<String, String> map = new HashMap<>();
        Matcher matcher = Pattern.compile("--(\\w+)\\s+([^\\s--]+)").matcher(input);
        while (matcher.find()) {
            map.put(matcher.group(1).toLowerCase(), matcher.group(2));
        }
        return map;
    }

    private static String extractValue(String json, String key) {
        Matcher m = Pattern.compile("\"" + key + "\":\\s*([0-9.]+)").matcher(json);
        return m.find() ? m.group(1) : "0.0";
    }
}