package com.BackendProyectoWeb.ProyectoWeb.Repositorio;

import com.BackendProyectoWeb.ProyectoWeb.Modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmailAndPassword(String email, String password);
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByEmailAndVerificationCode(String email, String verificationCode);
}