package com.BackendProyectoWeb.ProyectoWeb.Repositorio;

import com.BackendProyectoWeb.ProyectoWeb.Modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCorreoAndPassword(String correo, String password);
    Optional<Usuario> findByCorreo(String correo);
}