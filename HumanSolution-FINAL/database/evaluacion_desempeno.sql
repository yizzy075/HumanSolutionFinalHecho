-- =====================================================
-- Script de creación de tabla: evaluacion_desempeno
-- Proyecto: HumanSolution
-- Módulo: Evaluación de Desempeño
-- =====================================================

CREATE TABLE evaluacion_desempeno (
    id UUID PRIMARY KEY,
    id_usuario UUID NOT NULL,
    fecha DATE NOT NULL,
    calificacion INTEGER NOT NULL,
    observacion VARCHAR(500) NOT NULL,

    -- Constraints
    CONSTRAINT fk_evaluacion_usuario FOREIGN KEY (id_usuario)
        REFERENCES usuario(id) ON DELETE RESTRICT,

    CONSTRAINT unique_usuario_fecha UNIQUE(id_usuario, fecha),

    CONSTRAINT chk_calificacion_rango
        CHECK (calificacion >= 1 AND calificacion <= 10),

    CONSTRAINT chk_fecha_no_futura
        CHECK (fecha <= CURRENT_DATE),

    CONSTRAINT chk_observacion_longitud
        CHECK (LENGTH(observacion) >= 5 AND LENGTH(observacion) <= 500)
);

-- Índices para mejorar rendimiento
CREATE INDEX idx_evaluacion_usuario ON evaluacion_desempeno(id_usuario);
CREATE INDEX idx_evaluacion_fecha ON evaluacion_desempeno(fecha);

-- Comentarios de documentación
COMMENT ON TABLE evaluacion_desempeno IS 'Almacena las evaluaciones de desempeño de los usuarios';
COMMENT ON COLUMN evaluacion_desempeno.id IS 'Identificador único de la evaluación (UUID)';
COMMENT ON COLUMN evaluacion_desempeno.id_usuario IS 'Referencia al usuario evaluado';
COMMENT ON COLUMN evaluacion_desempeno.fecha IS 'Fecha de la evaluación (no puede ser futura)';
COMMENT ON COLUMN evaluacion_desempeno.calificacion IS 'Calificación del desempeño (1-10)';
COMMENT ON COLUMN evaluacion_desempeno.observacion IS 'Observaciones de la evaluación (5-500 caracteres)';
