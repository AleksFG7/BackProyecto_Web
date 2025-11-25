package com.BackendProyectoWeb.ProyectoWeb.DTO;

public class ReservaDTO {
    private Long id_usuario;
    private Long id_paquete;

    public Long getIdUsuario() {
        return id_usuario;
    }

    public void setIdUsuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Long getIdPaquete() {
        return id_paquete;
    }

    public void setIdPaquete(Long idPaquete) {
        this.id_paquete = idPaquete;
    }
}
