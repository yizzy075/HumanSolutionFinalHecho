package co.edu.uco.HumanSolution.dto;

import co.edu.uco.HumanSolution.crosscutting.helper.TextHelper;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;

public class TipoHoraExtraDTO {

    private String id;
    private String nombre;
    private int recargo;

    public TipoHoraExtraDTO(String id, String nombre, int recargo) {
        setId(id);
        setNombre(nombre);
        setRecargo(recargo);
    }

    public TipoHoraExtraDTO() {
        setId(UUIDHelper.getDefaultUUIDAsString());
        setNombre(TextHelper.EMPTY);
        setRecargo(0);
    }

    public static TipoHoraExtraDTO create(String id, String nombre, int recargo) {
        return new TipoHoraExtraDTO(id, nombre, recargo);
    }

    public static TipoHoraExtraDTO create() {
        return new TipoHoraExtraDTO();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = TextHelper.getDefault(id, UUIDHelper.getDefaultUUIDAsString());
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }

    public int getRecargo() {
        return recargo;
    }

    public void setRecargo(int recargo) {
        this.recargo = recargo;
    }
}