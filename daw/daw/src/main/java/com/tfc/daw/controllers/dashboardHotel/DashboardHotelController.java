package com.tfc.daw.controllers.dashboardHotel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import com.tfc.daw.models.HuespedModel;
import com.tfc.daw.models.ReservaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tfc.daw.models.DatosEntradaSalidaDTO;
import com.tfc.daw.models.DatosGastoFront;
import com.tfc.daw.services.gestionReservas.GastosService;
import com.tfc.daw.services.gestionReservas.GestionDatosReservaService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class DashboardHotelController {
    @Autowired
    private GestionDatosReservaService gestionDatosReservaService;

    @Autowired
    private GastosService gastosService;

    @GetMapping("/recepcion")
    public ResponseEntity<?> gestionDeReservas(HttpServletRequest request) {

        // HttpSession session = request.getSession(false);

        // if(session != null) {
            return ResponseEntity.ok().body(new ClassPathResource("/static/html/dashboardHotel/gestionReserva/dashboardGestionReservas.html"));
        // }
        // return ResponseEntity.status(HttpStatus.FOUND).header("Location", "/login").build();
    }


    @GetMapping("/servicios")
    public ResponseEntity<?> gestionGastos(HttpServletRequest request) {

        // HttpSession session = request.getSession(false);

        // if(session != null) {
            return ResponseEntity.ok().body(new ClassPathResource("/static/html/dashboardHotel/gestionGastos/dashboardGastos.html"));
        //}
        // return ResponseEntity.status(HttpStatus.FOUND).header("Location", "/login").build();
    }


    @GetMapping("/inforeserva/{codigoreserva}")
    public ResponseEntity<?> paginaInfoReserva(HttpServletRequest request) {

        // HttpSession session = request.getSession(false);

        // if(session != null) {
            return ResponseEntity.ok().body(new ClassPathResource("/static/html/dashboardHotel/gestionReserva/dashboardInfoReserva.html"));
        // }
        // return ResponseEntity.status(HttpStatus.FOUND).header("Location", "/login").build();

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

    @PostMapping("/addgasto")
    public void anadirGasto(@RequestBody DatosGastoFront gasto) {
        this.gastosService.anadirGasto(gasto);
    }

}