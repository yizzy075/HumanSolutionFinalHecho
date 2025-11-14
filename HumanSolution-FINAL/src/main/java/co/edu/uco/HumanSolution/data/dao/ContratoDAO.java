package co.edu.uco.HumanSolution.data.dao;

import co.edu.uco.HumanSolution.entity.ContratoEntity;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ContratoDAO {

    void create(ContratoEntity entity);

    List<ContratoEntity> read(ContratoEntity entity);

    void update(ContratoEntity entity);

    void delete(UUID id);

    boolean existsContratoVigenteByUsuario(UUID idUsuario);

    // ED-04: Verificar si existe un contrato vigente para un usuario en una fecha específica
    boolean existsContratoVigenteByUsuarioAndFecha(UUID idUsuario, LocalDate fecha);

    // Método para obtener contratos por usuario
    List<ContratoEntity> findByUsuario(UUID idUsuario);
}