package co.edu.uco.HumanSolution.entity;

import co.edu.uco.HumanSolution.crosscutting.helper.TextHelper;
import java.util.UUID;

public class TipoHoraExtraEntity extends Entity {

    private String nombre;
    private int recargo;

    public TipoHoraExtraEntity(UUID id, String nombre, int recargo) {
        super(id);
        setNombre(nombre);
        setRecargo(recargo);
    }

    public TipoHoraExtraEntity() {
        super();
        setNombre(TextHelper.EMPTY);
        setRecargo(0);
    }

    @Override
    public String name() {
        return "";
    }

    public static TipoHoraExtraEntity create(UUID id, String nombre, int recargo) {
        return new TipoHoraExtraEntity(id, nombre, recargo);
    }

    public static TipoHoraExtraEntity create() {
        return new TipoHoraExtraEntity();
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
        this.recargo = Math.max(0, Math.min(recargo, 200));
    }
}