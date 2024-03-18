package com.tfc.daw;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tfc.daw.models.DatosGastoFront;
import com.tfc.daw.models.GastosModel;
import com.tfc.daw.repositories.webHotel.GastosRespository;
import com.tfc.daw.repositories.webHotel.ReservaRepository;
import com.tfc.daw.services.gestionReservas.GastosService;

@ExtendWith(MockitoExtension.class)
public class GastosServiceTest {

    @Mock
    private GastosRespository gastosRespository;
    @Mock
    private ReservaRepository reservaRepository;

    @InjectMocks
    private GastosService gastosService;

     @Test
    public void testAnadirGasto() {
        // Datos de ejemplo
        GastosModel gastos = new GastosModel("pizza", "N", 1.1f, "xxxxxxxx");
        DatosGastoFront gastoFront = new DatosGastoFront("pizza", "1.1", "1");
        // Configuración del comportamiento del mock
        when(reservaRepository.obtenerCodigoReserva(Integer.parseInt(gastoFront.getNumHabitacion()))).thenReturn(gastos.getReserva_codigo());
        when(gastosRespository.save(gastos)).thenReturn(gastos);
        // Llamada al método que queremos probar
        GastosModel resultado = gastosService.anadirGasto(gastoFront);
        // Verificación de que el método del repositorio fue llamado con el código de reserva correcto
        verify(gastosRespository).save(gastos);
        verify(reservaRepository).obtenerCodigoReserva(Integer.parseInt(gastoFront.getNumHabitacion()));

        //Assertion para verificar que el resultado contiene la reserva esperada
        assertNotNull(gastoFront);
        assertNotNull(gastos);
        assertNotNull(gastoFront.getNumHabitacion());
        assertNotNull(gastos.getReserva_codigo());
        assertEquals(resultado, gastos);
        assertEquals(gastos.getConcepto(), gastoFront.getConcepto());
        assertEquals(gastos.getPrecio(), Float.parseFloat(gastoFront.getPrecio()));
    }
}
