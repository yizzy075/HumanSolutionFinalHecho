package co.edu.uco.HumanSolution.entity;

import co.edu.uco.HumanSolution.crosscutting.helper.TextHelper;
import jakarta.persistence.*;
import java.util.UUID;

@jakarta.persistence.Entity
@Table(name = "rol")
public class RolEntity extends Entity {

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    public RolEntity(UUID id, String nombre) {
        super(id);
        setNombre(nombre);
    }

    public RolEntity() {
        super();
        setNombre(TextHelper.EMPTY);
    }

    @Override
    public String name() {
        return "";
    }

    public static RolEntity create(UUID id, String nombre) {
        return new RolEntity(id, nombre);
    }

    public static RolEntity create() {
        return new RolEntity();
    }

    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }
}