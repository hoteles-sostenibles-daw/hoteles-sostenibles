package com.tfc.daw.controllers.dashboardHotel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import com.tfc.daw.models.HuespedModel;
import com.tfc.daw.models.ReservaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tfc.daw.models.DatosEntradaSalidaDTO;

import com.tfc.daw.services.gestionReservas.GestionDatosReservaService;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class DashboardHotelController {
    @Autowired
    private GestionDatosReservaService gestionDatosReservaService;
    @GetMapping("/recepcion")
    public Resource gestionDeReservas() {
        return new ClassPathResource("/static/html/dashboardHotel/gestionReserva/dashboardGestionReservas.html");
    }
    @GetMapping("/inforeserva/{codigoreserva}")
    public Resource paginaInfoReserva() {
        return new ClassPathResource("/static/html/dashboardHotel/gestionReserva/dashboardInfoReserva.html");
    }
    @PostMapping("/fechaentrada")
    public ArrayList<DatosEntradaSalidaDTO> obtenerFechaEntrada(@RequestBody String fecha) {

        return this.gestionDatosReservaService.obtenerFechaEntrada(fecha);

    }
    @PostMapping("/fechasalida")
    public ArrayList<DatosEntradaSalidaDTO> obtenerFechaSalida(@RequestBody String fecha) {
        return this.gestionDatosReservaService.obtenerFechaSalida(fecha);
    }

    @GetMapping("/actualizarcheckin/{codigoReserva}")
    public String actualizarCheckIn( @PathVariable String codigoReserva) {
        return  this.gestionDatosReservaService.actualizarCheckIn(codigoReserva);
    }

    @GetMapping("/actualizarcheckout/{codigoReserva}")
    public void actualizarCheckOut( @PathVariable String codigoReserva) {
        this.gestionDatosReservaService.actualizarCheckOut(codigoReserva);
    }
    @PostMapping("/obtenerinforeserva")
    public Boolean obtenerInfoReserva(@RequestBody String codigoReserva) {
        return this.gestionDatosReservaService.obtenerInfoReserva(codigoReserva);
    }

    @PostMapping("/cargarinforeserva")
    public Optional<ReservaModel> cargarInfoReserva(@RequestBody String urlReserva) {
        String[] codigoReserva = urlReserva.split("/");

        return this.gestionDatosReservaService.cargarInfoReserva(codigoReserva[codigoReserva.length-1]);
    }

    @PostMapping("/cargarhuespedreserva")
    public Optional<HuespedModel> cargarHuespedReserva(@RequestBody String urlReserva) {
        String[] codigoReserva = urlReserva.split("/");

        return this.gestionDatosReservaService.cargarHuespedReserva(codigoReserva[codigoReserva.length-1]);
    }

    @PostMapping("/guardarimagen")
    public void cargarImagen(@RequestParam("image") MultipartFile imagen, @RequestParam("dni") String dni) throws IOException {
        this.gestionDatosReservaService.guardarImagen(imagen, dni);
    }

    @GetMapping("/cargarimagen/{dni}")
    public ResponseEntity<byte[]> cargarImagen(@PathVariable String dni) throws IOException {
       return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(this.gestionDatosReservaService.cargarImagen(dni));
    }
}