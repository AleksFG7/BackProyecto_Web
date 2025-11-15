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
@CrossOrigin(origins = "*") // Permite peticiones desde tu frontend
public class UsuarioController {

    @Autowired
    private UsuarioServicio usuarioService;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Usuario datos) {
        Optional<Usuario> usuario = usuarioService.login(datos.getCorreo(), datos.getPassword());
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

        if (usuarioService.existeCorreo(nuevoUsuario.getCorreo())) {
            response.put("success", false);
            response.put("mensaje", "El correo ya está registrado");
        } else {
            usuarioService.registrar(nuevoUsuario);
            response.put("success", true);
            response.put("mensaje", "Usuario registrado correctamente");
        }

        return response;
    }
}