package com.BackendProyectoWeb.ProyectoWeb.Repositorio;

import com.BackendProyectoWeb.ProyectoWeb.Modelo.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaRepositorio extends JpaRepository<Reserva, Long> {
    List<Reserva> findByUsuarioId(Long id_usuario);
}
