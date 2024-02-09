package com.tfc.daw.services.gestionReservas;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfc.daw.models.DatosEntradaSalidaDTO;
import com.tfc.daw.models.ReservaModel;
import com.tfc.daw.repositories.webHotel.ReservaRepository;

@Service

public class GestionDatosReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    public ArrayList<DatosEntradaSalidaDTO> obtenerFechaEntrada(String fecha) {

        ArrayList<DatosEntradaSalidaDTO> listaEntradasFront = new ArrayList<>();

        ArrayList<ReservaModel> listaEntradas = this.reservaRepository.buscarFechaEntrada(fecha);
        if (listaEntradas.size() > 0) {
            for (ReservaModel reserva : listaEntradas) {
                DatosEntradaSalidaDTO datos = new DatosEntradaSalidaDTO();
                datos.setCodigoReserva(reserva.getCodigo());
                datos.setDni(reserva.getHuesped_dni());
                datos.setCheckIn(reserva.getCheck_in());
                listaEntradasFront.add(datos);
            }
        }

        return listaEntradasFront;

    }

    public ArrayList<DatosEntradaSalidaDTO> obtenerFechaSalida(String fecha) {

        ArrayList<DatosEntradaSalidaDTO> listaSalidasFront = new ArrayList<>();

        ArrayList<ReservaModel> listaSalidas = this.reservaRepository.buscarFechaSalida(fecha);
        if (listaSalidas.size() > 0) {
            for (ReservaModel reserva : listaSalidas) {
                DatosEntradaSalidaDTO datos = new DatosEntradaSalidaDTO();
                datos.setCodigoReserva(reserva.getCodigo());
                datos.setDni(reserva.getHuesped_dni());
                datos.setCheckIn(reserva.getCheck_in());
                listaSalidasFront.add(datos);
            }
        }

        return listaSalidasFront;

    }
}
