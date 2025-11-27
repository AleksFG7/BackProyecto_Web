package com.BackendProyectoWeb.ProyectoWeb.Servicio;

import com.BackendProyectoWeb.ProyectoWeb.DTO.ReservaDTO;
import com.BackendProyectoWeb.ProyectoWeb.Modelo.Reserva;
import com.BackendProyectoWeb.ProyectoWeb.Modelo.Usuario;
import com.BackendProyectoWeb.ProyectoWeb.Modelo.Paquete;
import com.BackendProyectoWeb.ProyectoWeb.Repositorio.ReservaRepositorio;
import com.BackendProyectoWeb.ProyectoWeb.Repositorio.UsuarioRepositorio;
import com.BackendProyectoWeb.ProyectoWeb.Repositorio.PaqueteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaServicio {
    @Autowired
    private final ReservaRepositorio reservaRepositorio;
    @Autowired
    private final UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private final PaqueteRepositorio paqueteRepositorio;

    public ReservaServicio(
            ReservaRepositorio reservaRepositorio,
            UsuarioRepositorio usuarioRepositorio,
            PaqueteRepositorio paqueteRepositorio
    ) {
        this.reservaRepositorio = reservaRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.paqueteRepositorio = paqueteRepositorio;
    }

    public Reserva crearReserva(Long idUsuario, Long idPaquete) {

        Usuario usuario = usuarioRepositorio.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Paquete paquete = paqueteRepositorio.findById(idPaquete)
                .orElseThrow(() -> new RuntimeException("Paquete no encontrado"));

        //Contar cuántas reservas tiene el usuario
        int count = reservaRepositorio.countByUsuarioId(idUsuario);
        Reserva reserva = new Reserva();
        reserva.setUsuario(usuario);
        reserva.setPaquete(paquete);


        //Asignar el número siguiente
        reserva.setNumeroReserva(count + 1);

        return reservaRepositorio.save(reserva);
    }
}
