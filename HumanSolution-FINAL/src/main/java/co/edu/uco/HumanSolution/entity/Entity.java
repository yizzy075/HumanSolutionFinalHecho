package co.edu.uco.HumanSolution.entity;

import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.util.UUID;

@MappedSuperclass  // ← IMPORTANTE: Usar @MappedSuperclass en lugar de @Entity
public abstract class Entity {

    @Id  // ← AGREGAR ESTA ANOTACIÓN
    @Column(name = "id", nullable = false)
    private UUID id;

    protected Entity() {
        setId(UUIDHelper.getDefaultUUID());
    }

    protected Entity(UUID id) {
        setId(id);
    }

    public UUID getId() {
        return id;
    }

    protected void setId(UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefaultUUID());
    }

    public abstract String name();
}