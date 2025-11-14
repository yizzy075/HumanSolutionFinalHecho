package co.edu.uco.HumanSolution.data.dao;

import co.edu.uco.HumanSolution.entity.EvaluacionDesempenoEntity;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EvaluacionDesempenoDAO {

    void create(EvaluacionDesempenoEntity entity);

    List<EvaluacionDesempenoEntity> read(EvaluacionDesempenoEntity entity);

    void update(EvaluacionDesempenoEntity entity);

    void delete(UUID id);

    // ED-02: Verificar si existe una evaluación para un usuario en una fecha específica
    boolean existsByUsuarioIdAndFecha(UUID idUsuario, LocalDate fecha);

    // ED-02: Buscar evaluación por usuario y fecha (para validar actualizaciones)
    Optional<EvaluacionDesempenoEntity> findByUsuarioIdAndFecha(UUID idUsuario, LocalDate fecha);

    // Método para obtener evaluaciones por usuario
    List<EvaluacionDesempenoEntity> findByUsuario(UUID idUsuario);
}