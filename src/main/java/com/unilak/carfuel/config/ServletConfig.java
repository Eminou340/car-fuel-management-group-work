/**
 * Names & Registration Numbers:
 * - Eminou Abdou : 26502/2024
 * - Bayambaze Jean Fred : 26398/2024
 * - Maaz montasir osman : 26812/2024
 */
package com.unilak.carfuel.config;

import com.unilak.carfuel.services.CarFuelService;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {

    @Bean
    public ServletRegistrationBean<FuelStatsServlet> fuelStatsServlet(CarFuelService service) {
        FuelStatsServlet servlet = new FuelStatsServlet(service);
        ServletRegistrationBean<FuelStatsServlet> bean = new ServletRegistrationBean<>(servlet, "/servlet/fuel-stats");
        bean.setLoadOnStartup(1);
        return bean;
    }
}