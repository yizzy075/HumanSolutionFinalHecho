package co.edu.uco.HumanSolution.domain;

import java.util.UUID;

/**
 * Entidad de dominio que representa un usuario del sistema.
 */
public class Usuario {

    private UUID id;
    private String nombre;
    private String email;
    private String cargo;

    // Constructor vacío
    public Usuario() {
    }

    // Constructor completo
    public Usuario(UUID id, String nombre, String email, String cargo) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.cargo = cargo;
    }

    // Getters y Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", cargo='" + cargo + '\'' +
                '}';
    }
}
