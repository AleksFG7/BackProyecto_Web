package com.BackendProyectoWeb.ProyectoWeb.Controlador;

import com.BackendProyectoWeb.ProyectoWeb.DTO.SuscripcionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/newsletter")
@CrossOrigin(origins = {"http://127.0.0.1:5501", "http://localhost:5501"})
public class NewsLetterController {
    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/suscribir")
    public ResponseEntity<String> suscribir(@RequestBody SuscripcionDTO dto) {

        try {
            // Crear el mensaje
            SimpleMailMessage mensaje = new SimpleMailMessage();
            mensaje.setTo(dto.getEmail());
            mensaje.setSubject("Bienvenido a AuraTours ✈️");
            mensaje.setText("Hola " + dto.getNombre() + ",\n\n"
                    + "Gracias por suscribirte a AuraTours.\n"
                    + "Pronto recibirás las novedades de la semana.\n\n"
                    + "¡Gracias por confiar en nosotros!");

            // Enviar correo
            mailSender.send(mensaje);

            return ResponseEntity.ok("Suscripción exitosa. Correo enviado.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al enviar correo: " + e.getMessage());
        }
    }
}
