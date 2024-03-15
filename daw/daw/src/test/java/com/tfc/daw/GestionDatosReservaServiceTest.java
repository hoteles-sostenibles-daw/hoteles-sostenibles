package com.tfc.daw;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tfc.daw.models.HuespedModel;
import com.tfc.daw.models.ReservaModel;
import com.tfc.daw.repositories.webHotel.HuespedRepository;
import com.tfc.daw.repositories.webHotel.ReservaRepository;
import com.tfc.daw.services.gestionReservas.GestionDatosReservaService;

@ExtendWith(MockitoExtension.class)
public class GestionDatosReservaServiceTest {

    @Mock
    private ReservaRepository reservaRepository;
    @Mock
    private HuespedRepository huespedRepository;

    @InjectMocks
    private GestionDatosReservaService gestionDatosReservaService;

    @Test
    public void testCargarInfoReserva() {
        // Datos de ejemplo
        String codigoReserva = "12345678F232024";
        ReservaModel reserva = new ReservaModel(codigoReserva, "xxx", "xxx", "s", "s", 0, "xxx", "xxx", "xxx");

        // Configuración del comportamiento del mock
        when(reservaRepository.findById(codigoReserva)).thenReturn(Optional.of(reserva));

        // Llamada al método que queremos probar
        Optional<ReservaModel> resultado = gestionDatosReservaService.cargarInfoReserva(codigoReserva);

        // Verificación de que el método del repositorio fue llamado con el código de reserva correcto
        verify(reservaRepository).findById(codigoReserva);

        //Assertion para verificar que el resultado contiene la reserva esperada
        assertNotNull(codigoReserva);
        assertNotNull(resultado.get().getCodigo());
        assertTrue(codigoReserva instanceof String);
        assertTrue(resultado.get().getCodigo() instanceof String);
        assertTrue(resultado.isPresent());
        assertEquals(codigoReserva, resultado.get().getCodigo());
    }

    @Test
    public void testObtenerEmail() {
        // Datos de ejemplo
        String dni = "12345678F";
        HuespedModel huesped = new HuespedModel(dni, "example@example.com", "xxx", "xxx", null);

        // Configuración del comportamiento del mock
        when(huespedRepository.findById(dni)).thenReturn(Optional.of(huesped));

        // Llamada al método que queremos probar
        String resultado = gestionDatosReservaService.obtenerEmail(dni);

        // Verificación de que el método del repositorio fue llamado con el código de reserva correcto
        verify(huespedRepository).findById(dni);

        //Assertion para verificar que el resultado contiene la reserva esperada
        assertNotNull(dni);
        assertEquals(9, dni.length());
        assertTrue(dni instanceof String);
        assertTrue(resultado instanceof String);
        assertTrue(resultado.contains("@"));
    }
}
