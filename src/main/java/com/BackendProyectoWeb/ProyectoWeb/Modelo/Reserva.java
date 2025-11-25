package com.BackendProyectoWeb.ProyectoWeb.Modelo;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "paquete_id_paquete", referencedColumnName = "id_paquete")
    private Paquete paquete;

    private LocalDateTime fechaReserva = LocalDateTime.now();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Paquete getPaquete() { return paquete; }
    public void setPaquete(Paquete paquete) { this.paquete = paquete; }

    public LocalDateTime getFechaReserva(){return fechaReserva;}
    public void setFechaReserva(LocalDateTime fechaReserva){this.fechaReserva = fechaReserva;}

}
