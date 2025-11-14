package co.edu.uco.HumanSolution.dto;

import co.edu.uco.HumanSolution.crosscutting.helper.TextHelper;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;

public class PuestoDTO {

    private String id;
    private String nombre;
    private String idUsuario;
    private String idUnidad;
    private String idEstado;
    private String idJefe;

    public PuestoDTO(String id, String nombre, String idUsuario, String idUnidad, String idEstado, String idJefe) {
        setId(id);
        setNombre(nombre);
        setIdUsuario(idUsuario);
        setIdUnidad(idUnidad);
        setIdEstado(idEstado);
        setIdJefe(idJefe);
    }

    public PuestoDTO() {
        setId(UUIDHelper.getDefaultUUIDAsString());
        setNombre(TextHelper.EMPTY);
        setIdUsuario(UUIDHelper.getDefaultUUIDAsString());
        setIdUnidad(UUIDHelper.getDefaultUUIDAsString());
        setIdEstado(UUIDHelper.getDefaultUUIDAsString());
        setIdJefe(UUIDHelper.getDefaultUUIDAsString());
    }

    public static PuestoDTO create(String id, String nombre, String idUsuario, String idUnidad, String idEstado, String idJefe) {
        return new PuestoDTO(id, nombre, idUsuario, idUnidad, idEstado, idJefe);
    }

    public static PuestoDTO create() {
        return new PuestoDTO();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(String idUnidad) {
        this.idUnidad = idUnidad;
    }

    public String getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }

    public String getIdJefe() {
        return idJefe;
    }

    public void setIdJefe(String idJefe) {
        this.idJefe = idJefe;
    }
}