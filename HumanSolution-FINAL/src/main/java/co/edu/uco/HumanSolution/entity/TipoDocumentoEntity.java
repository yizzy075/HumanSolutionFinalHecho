package co.edu.uco.HumanSolution.entity;

import co.edu.uco.HumanSolution.crosscutting.helper.TextHelper;
import java.util.UUID;

public class TipoDocumentoEntity extends Entity {

    private String nombre;
    private String descripcion;

    public TipoDocumentoEntity(UUID id, String nombre, String descripcion) {
        super(id);
        setNombre(nombre);
        setDescripcion(descripcion);
    }

    public TipoDocumentoEntity() {
        super();
        setNombre(TextHelper.EMPTY);
        setDescripcion(TextHelper.EMPTY);
    }

    @Override
    public String name() {
        return "";
    }

    public static TipoDocumentoEntity create(UUID id, String nombre, String descripcion) {
        return new TipoDocumentoEntity(id, nombre, descripcion);
    }

    public static TipoDocumentoEntity create() {
        return new TipoDocumentoEntity();
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
}