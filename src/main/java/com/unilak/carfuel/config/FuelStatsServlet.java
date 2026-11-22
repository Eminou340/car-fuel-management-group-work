/**
 * Names & Registration Numbers:
 * - Eminou Abdou : 26502/2024
 * - Bayambaze Jean Fred : 26398/2024
 * - Maaz montasir osman : 26812/2024
 */
package com.unilak.carfuel.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unilak.carfuel.dto.FuelStatsResponse;
import com.unilak.carfuel.exceptions.ResourceNotFoundException;
import com.unilak.carfuel.services.CarFuelService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class FuelStatsServlet extends HttpServlet {

    private final CarFuelService carFuelService;
    private final ObjectMapper objectMapper;

    public FuelStatsServlet(CarFuelService carFuelService) {
        this.carFuelService = carFuelService;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String carIdParam = req.getParameter("carId");
        if (carIdParam == null || carIdParam.trim().isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\": \"carId parameter is missing\"}");
            return;
        }

        try {
            Long carId = Long.parseLong(carIdParam.trim());
            FuelStatsResponse stats = carFuelService.getFuelStats(carId);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write(objectMapper.writeValueAsString(stats));
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\": \"carId must be an integer\"}");
        } catch (ResourceNotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\": \"Server error\"}");
        }
    }
}