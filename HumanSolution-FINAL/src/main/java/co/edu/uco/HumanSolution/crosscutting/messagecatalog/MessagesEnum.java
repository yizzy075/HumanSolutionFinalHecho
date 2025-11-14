package co.edu.uco.HumanSolution.crosscutting.messagecatalog;

public enum MessagesEnum {

    // GENERALES
    TECHNICAL_GENERAL_PROBLEM("HS-00001", "Se ha presentado un problema inesperado. Por favor intente de nuevo."),
    USER_GENERAL_PROBLEM("HS-00002", "Se ha presentado un problema procesando su solicitud."),

    // TIPO DOCUMENTO
    TIPO_DOCUMENTO_CREADO("HS-01001", "Tipo de documento creado exitosamente"),
    TIPO_DOCUMENTO_ACTUALIZADO("HS-01002", "Tipo de documento actualizado exitosamente"),
    TIPO_DOCUMENTO_ELIMINADO("HS-01003", "Tipo de documento eliminado exitosamente"),
    TIPO_DOCUMENTO_CONSULTADO("HS-01004", "Tipos de documento consultados exitosamente"),
    TIPO_DOCUMENTO_NOMBRE_DUPLICADO("HS-01005", "Ya existe un tipo de documento con ese nombre"),
    TIPO_DOCUMENTO_NOMBRE_VACIO("HS-01006", "El nombre del tipo de documento es obligatorio"),
    TIPO_DOCUMENTO_DESCRIPCION_VACIA("HS-01007", "La descripción del tipo de documento es obligatoria"),
    TIPO_DOCUMENTO_NOMBRE_LARGO("HS-01008", "El nombre no puede exceder 50 caracteres"),
    TIPO_DOCUMENTO_DESCRIPCION_LARGA("HS-01009", "La descripción no puede exceder 200 caracteres"),
    TIPO_DOCUMENTO_NO_EXISTE("HS-01010", "El tipo de documento no existe"),

    // EVALUACIÓN DE DESEMPEÑO
    EVALUACION_DESEMPENO_CREADA("HS-02001", "Evaluación de desempeño registrada exitosamente"),
    EVALUACION_DESEMPENO_CONSULTADA("HS-02002", "Evaluaciones consultadas exitosamente"),
    EVALUACION_DESEMPENO_ACTUALIZADA("HS-02003", "Evaluación de desempeño actualizada exitosamente"),
    EVALUACION_DESEMPENO_ELIMINADA("HS-02004", "Evaluación de desempeño eliminada exitosamente"),

    // ED-01: Datos obligatorios
    EVALUACION_FECHA_VACIA("HS-02005", "La fecha de evaluación es obligatoria"),
    EVALUACION_EVALUADOR_VACIO("HS-02006", "El evaluador es obligatorio"),
    EVALUACION_CRITERIOS_VACIOS("HS-02007", "Los criterios de evaluación son obligatorios"),
    EVALUACION_USUARIO_VACIO("HS-02008", "El usuario evaluado es obligatorio"),
    EVALUACION_CALIFICACION_VACIA("HS-02009", "La calificación es obligatoria"),

    // ED-02: No duplicados
    EVALUACION_DUPLICADA("HS-02010", "Ya existe una evaluación registrada para este usuario en la fecha indicada"),

    // ED-03: Fecha no futura
    EVALUACION_FECHA_FUTURA("HS-02011", "La fecha de evaluación no puede ser futura"),
    EVALUACION_FECHA_INVALIDA("HS-02012", "La fecha de evaluación no tiene un formato válido"),

    // ED-04: Contrato vigente
    EVALUACION_SIN_CONTRATO("HS-02013", "El usuario no tiene un contrato registrado"),
    EVALUACION_CONTRATO_NO_VIGENTE("HS-02014", "El usuario no tiene un contrato vigente en la fecha de evaluación"),
    CONTRATO_NO_ENCONTRADO("HS-02015", "No se encontró el contrato especificado"),

    // ED-05: Buenas prácticas - Formato, longitud, rango
    EVALUACION_CRITERIOS_LARGOS("HS-02016", "Los criterios no pueden exceder 500 caracteres"),
    EVALUACION_OBSERVACION_LARGA("HS-02017", "La observación no puede exceder 1000 caracteres"),
    EVALUACION_CALIFICACION_RANGO_INVALIDO("HS-02018", "La calificación debe estar entre 0 y 100"),
    EVALUACION_CALIFICACION_FORMATO_INVALIDO("HS-02019", "La calificación debe ser un número válido"),
    EVALUACION_EVALUADOR_LARGO("HS-02020", "El nombre del evaluador no puede exceder 100 caracteres"),
    EVALUACION_EVALUADOR_CORTO("HS-02021", "El nombre del evaluador debe tener al menos 3 caracteres"),
    EVALUACION_CRITERIOS_CORTOS("HS-02022", "Los criterios deben tener al menos 10 caracteres"),

    // Errores generales de evaluación
    EVALUACION_NO_EXISTE("HS-02023", "La evaluación de desempeño no existe"),
    EVALUACION_ERROR_REGISTRO("HS-02024", "Error al registrar la evaluación de desempeño"),
    EVALUACION_ERROR_CONSULTA("HS-02025", "Error al consultar las evaluaciones"),
    EVALUACION_ERROR_ACTUALIZACION("HS-02026", "Error al actualizar la evaluación"),
    EVALUACION_ERROR_ELIMINACION("HS-02027", "Error al eliminar la evaluación"),

    // Contrato
    CONTRATO_FECHA_INICIO_VACIA("HS-02028", "La fecha de inicio del contrato es obligatoria"),
    CONTRATO_FECHA_FIN_VACIA("HS-02029", "La fecha de fin del contrato es obligatoria"),
    CONTRATO_FECHA_FIN_ANTES_INICIO("HS-02030", "La fecha de fin del contrato no puede ser anterior a la fecha de inicio"),

    // CONEXIÓN
    CONNECTION_PROBLEM("HS-09001", "Se ha presentado un problema con la conexión a la base de datos"),
    CONNECTION_OPEN("HS-09002", "Conexión abierta exitosamente"),
    CONNECTION_CLOSE("HS-09003", "Conexión cerrada exitosamente");

    private String code;
    private String message;

    private MessagesEnum(String code, String message) {
        setCode(code);
        setMessage(message);
    }

    public String getCode() {
        return code;
    }

    private void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }
}