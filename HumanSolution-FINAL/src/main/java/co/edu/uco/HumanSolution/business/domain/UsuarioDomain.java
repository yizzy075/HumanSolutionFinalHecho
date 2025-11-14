package co.edu.uco.HumanSolution.business.domain;

import co.edu.uco.HumanSolution.crosscutting.helper.TextHelper;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import java.util.UUID;
import java.util.regex.Pattern;

public class UsuarioDomain extends Domain {

    private String documento;
    private String nombre;
    private String correo;
    private String contrasenia;
    private String idRol;  // ✅ CAMBIAR DE UUID A STRING

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$");

    private UsuarioDomain(UUID id, String documento, String nombre, String correo, String contrasenia, String idRol) {  // ✅ CAMBIAR PARÁMETRO
        super(id);
        setDocumento(documento);
        setNombre(nombre);
        setCorreo(correo);
        setContrasenia(contrasenia);
        setIdRol(idRol);
    }

    public static UsuarioDomain create(UUID id, String documento, String nombre, String correo, String contrasenia, String idRol) {  // ✅ CAMBIAR PARÁMETRO
        return new UsuarioDomain(id, documento, nombre, correo, contrasenia, idRol);
    }

    public static UsuarioDomain create() {
        return new UsuarioDomain(
                UUIDHelper.getDefaultUUID(),
                TextHelper.EMPTY,
                TextHelper.EMPTY,
                TextHelper.EMPTY,
                TextHelper.EMPTY,
                TextHelper.EMPTY  // ✅ CAMBIAR A STRING VACÍO
        );
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

    public String getIdRol() {  // ✅ CAMBIAR RETURN TYPE
        return idRol;
    }

    private void setIdRol(String idRol) {  // ✅ CAMBIAR PARAMETER TYPE
        this.idRol = TextHelper.applyTrim(idRol);  // ✅ CAMBIAR LÓGICA
    }

    public void validarDocumento() {
        if (TextHelper.isEmpty(documento)) {
            throw new IllegalArgumentException("El documento es obligatorio");
        }
        if (documento.length() < 5 || documento.length() > 15) {
            throw new IllegalArgumentException("El documento debe tener entre 5 y 15 caracteres");
        }
    }

    public void validarNombre() {
        if (TextHelper.isEmpty(nombre)) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (nombre.length() < 5 || nombre.length() > 50) {
            throw new IllegalArgumentException("El nombre debe tener entre 5 y 50 caracteres");
        }
    }

    public void validarCorreo() {
        if (TextHelper.isEmpty(correo)) {
            throw new IllegalArgumentException("El correo es obligatorio");
        }
        if (!EMAIL_PATTERN.matcher(correo).matches()) {
            throw new IllegalArgumentException("El formato del correo no es válido");
        }
        if (correo.length() > 100) {
            throw new IllegalArgumentException("El correo no puede exceder 100 caracteres");
        }
    }

    public void validarContrasenia() {
        if (TextHelper.isEmpty(contrasenia)) {
            throw new IllegalArgumentException("La contraseña es obligatoria");
        }
        if (!PASSWORD_PATTERN.matcher(contrasenia).matches()) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres, una mayúscula, un número y un carácter especial");
        }
        if (contrasenia.length() > 50) {
            throw new IllegalArgumentException("La contraseña no puede exceder 50 caracteres");
        }
    }

    public void validarIdRol() {
        if (TextHelper.isEmpty(idRol)) {  // ✅ CAMBIAR VALIDACIÓN
            throw new IllegalArgumentException("El rol es obligatorio");
        }
    }

    public void validar() {
        validarDocumento();
        validarNombre();
        validarCorreo();
        validarContrasenia();
        validarIdRol();
    }
}