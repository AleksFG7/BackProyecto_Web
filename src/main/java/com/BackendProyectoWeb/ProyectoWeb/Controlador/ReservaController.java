package com.BackendProyectoWeb.ProyectoWeb.Controlador;

import com.BackendProyectoWeb.ProyectoWeb.DTO.ReservaDTO;
import com.BackendProyectoWeb.ProyectoWeb.Modelo.Reserva;
import com.BackendProyectoWeb.ProyectoWeb.Repositorio.ReservaRepositorio;
import com.BackendProyectoWeb.ProyectoWeb.Servicio.ReservaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaServicio reservaServicio;
    @Autowired
    private ReservaRepositorio reservaRepositorio;

    public ReservaController(ReservaServicio reservaServicio) {
        this.reservaServicio = reservaServicio;
    }
    @PostMapping
    public ResponseEntity<?> crearReserva(@RequestBody ReservaDTO dto) {
        try {
            Reserva reserva = reservaServicio.crearReserva(dto.getIdUsuario(), dto.getIdPaquete());
            return ResponseEntity.ok(reserva);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/{idUsuario}")
    public List<Reserva> obtenerReservasPorUsuario(@PathVariable("idUsuario") Long idUsuario) {
        return reservaRepositorio.findByUsuarioId(idUsuario);
    }
}

