package co.edu.uco.HumanSolution.business.domain;

import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import co.edu.uco.HumanSolution.crosscutting.messagecatalog.MessagesEnum;
import co.edu.uco.HumanSolution.crosscutting.exception.BusinessException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class ContratoDomain extends Domain {

    private UUID idUsuario;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private BigDecimal sueldo;

    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1423500");

    private ContratoDomain(UUID id, UUID idUsuario, LocalDate fechaInicio, LocalDate fechaFin, BigDecimal sueldo) {
        super(id);
        setIdUsuario(idUsuario);
        setFechaInicio(fechaInicio);
        setFechaFin(fechaFin);
        setSueldo(sueldo);
    }

    public static ContratoDomain create(UUID id, UUID idUsuario, LocalDate fechaInicio, LocalDate fechaFin, BigDecimal sueldo) {
        return new ContratoDomain(id, idUsuario, fechaInicio, fechaFin, sueldo);
    }

    public static ContratoDomain create() {
        return new ContratoDomain(
                UUIDHelper.getDefaultUUID(),
                UUIDHelper.getDefaultUUID(),
                LocalDate.now(),
                null,
                BigDecimal.ZERO
        );
    }

    public UUID getIdUsuario() {
        return idUsuario;
    }

    private void setIdUsuario(UUID idUsuario) {
        this.idUsuario = UUIDHelper.getDefault(idUsuario, UUIDHelper.getDefaultUUID());
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    private void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio != null ? fechaInicio : LocalDate.now();
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    private void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public BigDecimal getSueldo() {
        return sueldo;
    }

    private void setSueldo(BigDecimal sueldo) {
        this.sueldo = sueldo != null ? sueldo : BigDecimal.ZERO;
    }

    public void validarIdUsuario() {
        if (UUIDHelper.isDefault(idUsuario)) {
            throw new BusinessException(
                    "El ID del usuario es obligatorio para el contrato",
                    "El usuario es obligatorio"
            );
        }
    }

    public void validarFechaInicio() {
        if (fechaInicio == null) {
            throw new BusinessException(
                    "La fecha de inicio del contrato es nula",
                    MessagesEnum.CONTRATO_FECHA_INICIO_VACIA.getMessage()
            );
        }
    }

    public void validarFechaFin() {
        if (fechaFin != null && fechaFin.isBefore(fechaInicio)) {
            throw new BusinessException(
                    "La fecha de fin " + fechaFin + " es anterior a la fecha de inicio " + fechaInicio,
                    MessagesEnum.CONTRATO_FECHA_FIN_ANTES_INICIO.getMessage()
            );
        }
    }

    public void validarSueldo() {
        if (sueldo.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException(
                    "El sueldo debe ser mayor a cero",
                    "El sueldo debe ser mayor a cero"
            );
        }
        if (sueldo.compareTo(SALARIO_MINIMO) < 0) {
            throw new BusinessException(
                    "El sueldo no puede ser menor al salario mínimo",
                    "El sueldo no puede ser menor al salario mínimo"
            );
        }
    }

    // Método para verificar si el contrato está vigente en una fecha específica
    public boolean estaVigenteEn(LocalDate fecha) {
        if (fecha == null || fechaInicio == null) {
            return false;
        }

        boolean despuesDeFechaInicio = !fecha.isBefore(fechaInicio);
        boolean antesDeFechaFin = fechaFin == null || !fecha.isAfter(fechaFin);

        return despuesDeFechaInicio && antesDeFechaFin;
    }

    public void validar() {
        validarIdUsuario();
        validarFechaInicio();
        validarFechaFin();
        validarSueldo();
    }
}
