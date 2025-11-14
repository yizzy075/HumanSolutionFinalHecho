package co.edu.uco.HumanSolution.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RolDTO {

    private String id;
    private String nombre;

    public RolDTO() {
    }

    // ✅ AGREGAR ESTE CONSTRUCTOR
    public RolDTO(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // ✅ AGREGAR ESTE MÉTODO ESTÁTICO
    public static RolDTO create(String id, String nombre) {
        return new RolDTO(id, nombre);
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("nombre")
    public String getNombre() {
        return nombre;
    }

    @JsonProperty("nombre")
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "RolDTO{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}