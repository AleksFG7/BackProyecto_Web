package com.BackendProyectoWeb.ProyectoWeb.Modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    @Column(unique = true)
    private String email;
    private String password;
    private String verificationCode;
    private boolean verified;


    public Usuario() {}

    public Usuario(String nombre, String email, String password) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getVerificationCode(){return verificationCode;}
    public void setVerificationCode(String verificationCode){this.verificationCode = verificationCode;}

    public boolean isVerified(){return verified;}
    public void setVerified(boolean verified){this.verified = verified;}
}