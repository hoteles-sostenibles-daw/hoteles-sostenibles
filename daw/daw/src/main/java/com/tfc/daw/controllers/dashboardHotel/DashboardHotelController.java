package com.tfc.daw.controllers.dashboardHotel;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardHotelController {
    
    @GetMapping("/gestionreservas")
    public Resource gestionReservas(){
        return new ClassPathResource("/static/html/dashboardHotel/gestionReserva/dashboardGestionReservas.html");
    }
    @GetMapping("/gestionreservas/reserva-{numReserva}")
    public Resource infoReserva(@PathVariable int numReserva){
        return new ClassPathResource("/static/html/dashboardHotel/gestionReserva/dashboardInfoReserva.html");
    }
    @GetMapping("/gestionreservas/habitacion-{numHabitacion}")
    public Resource infoGastos(@PathVariable int numHabitacion){
        return new ClassPathResource("/static/html/dashboardHotel/gestionReserva/dashboardInfoGastos.html");
    }
    @GetMapping("/gastos")
    public Resource infoGastos(){
        return new ClassPathResource("/static/html/dashboardHotel/gestionGastos/dashboardGastos.html");
    }
}
