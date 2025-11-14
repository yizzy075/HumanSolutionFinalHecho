package co.edu.uco.HumanSolution.business.domain;

import co.edu.uco.HumanSolution.crosscutting.helper.TextHelper;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import java.util.UUID;

public class EstadoSolicitudDomain extends Domain {

    private String nombre;

    private EstadoSolicitudDomain(UUID id, String nombre) {
        super(id);
        setNombre(nombre);
    }

    public static EstadoSolicitudDomain create(UUID id, String nombre) {
        return new EstadoSolicitudDomain(id, nombre);
    }

    public static EstadoSolicitudDomain create() {
        return new EstadoSolicitudDomain(UUIDHelper.getDefaultUUID(), TextHelper.EMPTY);
    }

    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }

    public void validarNombre() {
        if (TextHelper.isEmpty(nombre)) {
            throw new IllegalArgumentException("El nombre del estado de solicitud es obligatorio");
        }
        if (nombre.length() > 50) {
            throw new IllegalArgumentException("El nombre no puede exceder 50 caracteres");
        }
    }

    public void validar() {
        validarNombre();
    }
}