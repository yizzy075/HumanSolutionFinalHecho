package co.edu.uco.HumanSolution.business.domain;

import co.edu.uco.HumanSolution.crosscutting.helper.TextHelper;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import java.util.UUID;

public class TipoHoraExtraDomain extends Domain {

    private String nombre;
    private int recargo;

    private TipoHoraExtraDomain(UUID id, String nombre, int recargo) {
        super(id);
        setNombre(nombre);
        setRecargo(recargo);
    }

    public static TipoHoraExtraDomain create(UUID id, String nombre, int recargo) {
        return new TipoHoraExtraDomain(id, nombre, recargo);
    }

    public static TipoHoraExtraDomain create() {
        return new TipoHoraExtraDomain(UUIDHelper.getDefaultUUID(), TextHelper.EMPTY, 0);
    }

    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }

    public int getRecargo() {
        return recargo;
    }

    private void setRecargo(int recargo) {
        this.recargo = recargo;
    }

    public void validarNombre() {
        if (TextHelper.isEmpty(nombre)) {
            throw new IllegalArgumentException("El nombre del tipo de hora extra es obligatorio");
        }
        if (nombre.length() > 100) {
            throw new IllegalArgumentException("El nombre no puede exceder 100 caracteres");
        }
    }

    public void validarRecargo() {
        if (recargo < 0) {
            throw new IllegalArgumentException("El recargo no puede ser negativo");
        }
        if (recargo > 200) {
            throw new IllegalArgumentException("El recargo no puede exceder 200%");
        }
    }

    public void validar() {
        validarNombre();
        validarRecargo();
    }
}