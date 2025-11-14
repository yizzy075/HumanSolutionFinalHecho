package co.edu.uco.HumanSolution.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UsuarioDTO {

    private String id;
    private String documento;
    private String nombre;
    private String correo;
    private String contrasena;
    private RolDTO rol;  // ✅ Objeto RolDTO, no String idRol

    public UsuarioDTO() {
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("documento")
    public String getDocumento() {
        return documento;
    }

    @JsonProperty("documento")
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    @JsonProperty("nombre")
    public String getNombre() {
        return nombre;
    }

    @JsonProperty("nombre")
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @JsonProperty("correo")
    public String getCorreo() {
        return correo;
    }

    @JsonProperty("correo")
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @JsonProperty("contrasena")
    public String getContrasena() {
        return contrasena;
    }

    @JsonProperty("contrasena")
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @JsonProperty("rol")
    public RolDTO getRol() {
        return rol;
    }

    @JsonProperty("rol")
    public void setRol(RolDTO rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "id='" + id + '\'' +
                ", documento='" + documento + '\'' +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", rol=" + rol +
                '}';
    }
}