package com.BackendProyectoWeb.ProyectoWeb.Modelo;
import jakarta.persistence.*;

@Entity
@Table(name = "paquetes")
public class Paquete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paquete")
    private Long id_paquete;

    private String nombre;
    private Double precio;

    // Getters y setters
    public Long getId() { return id_paquete; }
    public void setId(Long id) { this.id_paquete = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }
}
