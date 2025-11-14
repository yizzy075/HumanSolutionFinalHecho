package co.edu.uco.HumanSolution.entity;

import co.edu.uco.HumanSolution.crosscutting.helper.TextHelper;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

import java.util.UUID;

@jakarta.persistence.Entity
@Table(name = "usuario")
public class UsuarioEntity extends Entity {

    @Column(name = "numero_documento", nullable = false, length = 15)  // ✅ CAMBIAR AQUÍ
    private String documento;

    @Column(name = "nombre", nullable = false, length = 50)  // ✅ CAMBIAR A 50
    private String nombre;

    @Column(name = "correo", nullable = false, unique = true, length = 100)
    private String correo;

    @Column(name = "contrasena", nullable = false, length = 100)  // ✅ CAMBIAR NOMBRE
    private String contrasenia;

    @Column(name = "rol", nullable = false, length = 50)  // ✅ CAMBIAR A STRING
    private String idRol;

    public UsuarioEntity(UUID id, String documento, String nombre, String correo, String contrasenia, String idRol) {
        super(id);
        setDocumento(documento);
        setNombre(nombre);
        setCorreo(correo);
        setContrasenia(contrasenia);
        setIdRol(idRol);
    }

    public UsuarioEntity() {
        super();
        setDocumento(TextHelper.EMPTY);
        setNombre(TextHelper.EMPTY);
        setCorreo(TextHelper.EMPTY);
        setContrasenia(TextHelper.EMPTY);
        setIdRol(TextHelper.EMPTY);
    }

    @Override
    public String name() {
        return "usuario";
    }

    public static UsuarioEntity create(UUID id, String documento, String nombre, String correo, String contrasenia, String idRol) {
        return new UsuarioEntity(id, documento, nombre, correo, contrasenia, idRol);
    }

    public static UsuarioEntity create() {
        return new UsuarioEntity();
    }

    public String getDocumento() {
        return documento;
    }

    private void setDocumento(String documento) {
        this.documento = TextHelper.applyTrim(documento);
    }

    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }

    public String getCorreo() {
        return correo;
    }

    private void setCorreo(String correo) {
        this.correo = TextHelper.applyTrim(correo);
    }

    public String getContrasenia() {
        return contrasenia;
    }

    private void setContrasenia(String contrasenia) {
        this.contrasenia = TextHelper.applyTrim(contrasenia);
    }

    public String getIdRol() {
        return idRol;
    }

    private void setIdRol(String idRol) {
        this.idRol = TextHelper.applyTrim(idRol);
    }
}