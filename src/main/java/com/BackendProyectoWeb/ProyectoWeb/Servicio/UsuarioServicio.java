package com.BackendProyectoWeb.ProyectoWeb.Servicio;
import com.BackendProyectoWeb.ProyectoWeb.Modelo.Usuario;
import com.BackendProyectoWeb.ProyectoWeb.Repositorio.UsuarioRepositorio;
import com.BackendProyectoWeb.ProyectoWeb.Servicio.CorreoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepository;

    @Autowired
    private CorreoServicio correoServicio;

    public Optional<Usuario> login(String email, String password) {
        return usuarioRepository.findByEmailAndPassword(email, password);
    }

    public Usuario registrar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public boolean existeCorreo(String email) {
        return usuarioRepository.findByEmail(email).isPresent();
    }

    public void registrarConVerificacion(String nombre, String email, String password) {
        Usuario user = new Usuario();
        user.setNombre(nombre);
        user.setEmail(email);
        user.setPassword(password);
        user.setVerified(false);

        // Generar código de 6 dígitos
        String codigo = String.valueOf(100000 + new Random().nextInt(900000));
        user.setVerificationCode(codigo);

        usuarioRepository.save(user);

        correoServicio.enviarCodigo(email, codigo);
    }


    public boolean verificarCuenta(String email, String codigo) {
        return usuarioRepository.findByEmailAndVerificationCode(email, codigo)
                .map(u -> {
                    u.setVerified(true);
                    usuarioRepository.save(u);
                    return true;
                })
                .orElse(false);
    }

    private String generarCodigo() {
        return String.valueOf(100000 + new Random().nextInt(900000));
    }
}
