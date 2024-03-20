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

        String codigoReserva = "12345678F232024";
        ReservaModel reserva = new ReservaModel(codigoReserva, "xxx", "xxx", "s", "s", 0, "xxx", "xxx", "xxx");

        when(reservaRepository.findById(codigoReserva)).thenReturn(Optional.of(reserva));

        Optional<ReservaModel> resultado = gestionDatosReservaService.cargarInfoReserva(codigoReserva);

        verify(reservaRepository).findById(codigoReserva);

        assertNotNull(codigoReserva);
        assertNotNull(resultado.get().getCodigo());
        assertTrue(codigoReserva instanceof String);
        assertTrue(resultado.get().getCodigo() instanceof String);
        assertTrue(resultado.isPresent());
        assertEquals(codigoReserva, resultado.get().getCodigo());
    }

    @Test
    public void testObtenerEmail() {

        String dni = "12345678F";
        HuespedModel huesped = new HuespedModel(dni, "example@example.com", "xxx", "xxx", null);

        when(huespedRepository.findById(dni)).thenReturn(Optional.of(huesped));

        String resultado = gestionDatosReservaService.obtenerEmail(dni);

        verify(huespedRepository).findById(dni);

        assertNotNull(dni);
        assertEquals(9, dni.length());
        assertTrue(dni instanceof String);
        assertTrue(resultado instanceof String);
        assertTrue(resultado.contains("@"));
    }
}
