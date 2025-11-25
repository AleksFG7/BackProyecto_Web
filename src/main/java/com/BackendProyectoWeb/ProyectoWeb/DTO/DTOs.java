package com.BackendProyectoWeb.ProyectoWeb.DTO;

public class DTOs {
    public class RegistroDTO {
        private String nombre;
        private String correo;
        private String password;
        // getters y setters
        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }

        public String getCorreo() { return correo; }
        public void setCorreo(String correo) { this.correo = correo; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    public class VerificacionDTO {
        private String correo;
        private String codigo;
        // getters y setters
        public String getCorreo() { return correo; }
        public void setCorreo(String correo) { this.correo = correo; }


        public String getCodigo() { return codigo; }
        public void setCodigo(String codigo) { this.codigo = codigo; }
    }
}
