package com.BackendProyectoWeb.ProyectoWeb.Controlador;

import com.BackendProyectoWeb.ProyectoWeb.Modelo.Usuario;
import com.BackendProyectoWeb.ProyectoWeb.Servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Usuario datos) {

        Optional<Usuario> usuario = usuarioServicio.login(
                datos.getEmail(),
                datos.getPassword()
        );

        Map<String, Object> response = new HashMap<>();

        if (usuario.isPresent()) {
            response.put("success", true);
            response.put("nombre", usuario.get().getNombre());
        } else {
            response.put("success", false);
            response.put("mensaje", "Correo o contraseña incorrectos");
        }
        return response;
    }

    @PostMapping("/registro")
    public Map<String, Object> registrar(@RequestBody Usuario nuevoUsuario) {
        Map<String, Object> response = new HashMap<>();

        if (usuarioServicio.existeCorreo(nuevoUsuario.getEmail())) {
            response.put("success", false);
            response.put("mensaje", "El correo ya está registrado");
            return response;
        }

        usuarioServicio.registrarConVerificacion(
                nuevoUsuario.getNombre(),
                nuevoUsuario.getEmail(),
                nuevoUsuario.getPassword()
        );

        response.put("success", true);
        response.put("mensaje", "Se envió un código a tu correo");
        return response;
    }
}
