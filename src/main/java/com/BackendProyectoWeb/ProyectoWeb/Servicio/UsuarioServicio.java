package com.BackendProyectoWeb.ProyectoWeb.Servicio;

import com.BackendProyectoWeb.ProyectoWeb.Modelo.Usuario;
import com.BackendProyectoWeb.ProyectoWeb.Repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepositorio usuarioRepository;

    public Optional<Usuario> login(String correo, String password) {
        return usuarioRepository.findByCorreoAndPassword(correo, password);
    }

    public Usuario registrar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public boolean existeCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo).isPresent();
    }
}
