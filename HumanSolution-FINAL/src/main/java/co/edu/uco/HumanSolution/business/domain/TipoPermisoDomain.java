package co.edu.uco.HumanSolution.business.domain;

import co.edu.uco.HumanSolution.crosscutting.helper.TextHelper;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import java.util.UUID;

public class TipoPermisoDomain extends Domain {

    private String nombre;
    private String descripcion;

    private TipoPermisoDomain(UUID id, String nombre, String descripcion) {
        super(id);
        setNombre(nombre);
        setDescripcion(descripcion);
    }

    public static TipoPermisoDomain create(UUID id, String nombre, String descripcion) {
        return new TipoPermisoDomain(id, nombre, descripcion);
    }

    public static TipoPermisoDomain create() {
        return new TipoPermisoDomain(UUIDHelper.getDefaultUUID(), TextHelper.EMPTY, TextHelper.EMPTY);
    }

    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }

    public String getDescripcion() {
        return descripcion;
    }

    private void setDescripcion(String descripcion) {
        this.descripcion = TextHelper.applyTrim(descripcion);
    }

    public void validarNombre() {
        if (TextHelper.isEmpty(nombre)) {
            throw new IllegalArgumentException("El nombre del tipo de permiso es obligatorio");
        }
        if (nombre.length() > 100) {
            throw new IllegalArgumentException("El nombre no puede exceder 100 caracteres");
        }
    }

    public void validarDescripcion() {
        if (TextHelper.isEmpty(descripcion)) {
            throw new IllegalArgumentException("La descripción es obligatoria");
        }
        if (descripcion.length() > 500) {
            throw new IllegalArgumentException("La descripción no puede exceder 500 caracteres");
        }
    }

    public void validar() {
        validarNombre();
        validarDescripcion();
    }
}