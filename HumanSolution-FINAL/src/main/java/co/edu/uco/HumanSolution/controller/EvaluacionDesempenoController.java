package co.edu.uco.HumanSolution.controller;

import co.edu.uco.HumanSolution.dao.EvaluacionDesempenoDAO;
import co.edu.uco.HumanSolution.domain.EvaluacionDesempeno;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controlador REST para gestión de evaluaciones de desempeño.
 * Demuestra el uso correcto de los DAOs con DataSource inyectado.
 */
@RestController
@RequestMapping("/api/evaluaciones")
@CrossOrigin(origins = "http://localhost:4200")
public class EvaluacionDesempenoController {

    private final EvaluacionDesempenoDAO evaluacionDAO;

    /**
     * Constructor con inyección de dependencias del DAO.
     * Spring inyecta automáticamente el DAO que ya tiene el DataSource configurado.
     *
     * @param evaluacionDAO el DAO de evaluaciones
     */
    public EvaluacionDesempenoController(EvaluacionDesempenoDAO evaluacionDAO) {
        this.evaluacionDAO = evaluacionDAO;
    }

    /**
     * Verifica si existe una evaluación por su ID.
     * Este endpoint resuelve el error original "DAO-EXISTS: Error al verificar existencia".
     *
     * @param id el UUID de la evaluación
     * @return ResponseEntity con el resultado
     */
    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> exists(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            boolean exists = evaluacionDAO.exists(uuid);
            return ResponseEntity.ok(exists);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(false);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    /**
     * Obtiene todas las evaluaciones.
     *
     * @return lista de evaluaciones
     */
    @GetMapping
    public ResponseEntity<List<EvaluacionDesempeno>> findAll() {
        try {
            List<EvaluacionDesempeno> evaluaciones = evaluacionDAO.findAll();
            return ResponseEntity.ok(evaluaciones);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Obtiene una evaluación por su ID.
     *
     * @param id el UUID de la evaluación
     * @return la evaluación encontrada
     */
    @GetMapping("/{id}")
    public ResponseEntity<EvaluacionDesempeno> findById(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            return evaluacionDAO.findById(uuid)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Obtiene todas las evaluaciones de un usuario.
     *
     * @param idUsuario el UUID del usuario
     * @return lista de evaluaciones del usuario
     */
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<EvaluacionDesempeno>> findByUsuario(@PathVariable String idUsuario) {
        try {
            UUID uuid = UUID.fromString(idUsuario);
            List<EvaluacionDesempeno> evaluaciones = evaluacionDAO.findByUsuario(uuid);
            return ResponseEntity.ok(evaluaciones);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Crea una nueva evaluación.
     *
     * @param evaluacion la evaluación a crear
     * @return la evaluación creada
     */
    @PostMapping
    public ResponseEntity<EvaluacionDesempeno> create(@RequestBody EvaluacionDesempeno evaluacion) {
        try {
            // Validar que no exista una evaluación para el mismo usuario en la misma fecha
            if (evaluacionDAO.existsByUsuarioAndFecha(evaluacion.getIdUsuario(), evaluacion.getFecha())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }

            EvaluacionDesempeno created = evaluacionDAO.create(evaluacion);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Actualiza una evaluación existente.
     *
     * @param id el UUID de la evaluación
     * @param evaluacion los datos actualizados
     * @return la respuesta
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody EvaluacionDesempeno evaluacion) {
        try {
            UUID uuid = UUID.fromString(id);
            evaluacion.setId(uuid);

            boolean updated = evaluacionDAO.update(evaluacion);
            return updated ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Elimina una evaluación.
     *
     * @param id el UUID de la evaluación
     * @return la respuesta
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            boolean deleted = evaluacionDAO.delete(uuid);
            return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
