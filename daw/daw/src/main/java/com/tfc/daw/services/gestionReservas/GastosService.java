package com.tfc.daw.services.gestionReservas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfc.daw.models.DatosGastoFront;
import com.tfc.daw.models.GastosModel;
import com.tfc.daw.repositories.webHotel.GastosRespository;
import com.tfc.daw.repositories.webHotel.ReservaRepository;

@Service
public class GastosService {

    @Autowired
    private GastosRespository gastosRespository;

    @Autowired
    private ReservaRepository reservaRepository;

    public void anadirGasto(DatosGastoFront gasto) {
        GastosModel gastos = new GastosModel();
        gastos.setConcepto(gasto.getConcepto());
        gastos.setPagado("N");
        gastos.setPrecio(Float.parseFloat(gasto.getPrecio()));
        gastos.setReservaCodigo(this.reservaRepository.obtenerCodigoReserva(Integer.parseInt(gasto.getNumHabitacion())));
        this.gastosRespository.save(gastos);
    }
    
}
