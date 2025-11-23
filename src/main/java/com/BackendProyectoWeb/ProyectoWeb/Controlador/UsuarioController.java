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
@CrossOrigin(origins = {"http://127.0.0.1:5501", "http://localhost:5501"})

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
            // Usuario válido y verificado
            response.put("success", true);
            response.put("nombre", usuario.get().getNombre());
        } else {
            // ¿Existe pero no está verificado?
            Optional<Usuario> existe = usuarioServicio.findByEmail(datos.getEmail());

            if (existe.isPresent() && !existe.get().isVerified()) {
                response.put("success", false);
                response.put("mensaje", "Tu cuenta no está verificada");
            } else {
                response.put("success", false);
                response.put("mensaje", "Correo o contraseña incorrectos");
            }
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
        response.put("email", nuevoUsuario.getEmail());

        return response;
    }

    @PostMapping("/verificar")
    public Map<String, Object> verificarCodigo(@RequestBody Map<String, String> datos) {
        String email = datos.get("email");
        String codigo = datos.get("codigo");

        Map<String, Object> response = new HashMap<>();

        boolean valido = usuarioServicio.verificarCuenta(email, codigo);

        if (valido) {
            response.put("success", true);
            response.put("mensaje", "Cuenta verificada correctamente");
        } else {
            response.put("success", false);
            response.put("mensaje", "Código incorrecto");
        }

        System.out.println("📩 Email recibido: " + datos.get("email"));
        System.out.println("🔢 Código recibido: " + datos.get("codigo"));
        System.out.println("JSON recibido: " + datos);


        return response;
    }
}
