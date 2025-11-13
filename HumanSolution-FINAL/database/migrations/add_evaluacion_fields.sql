-- Script para agregar campos faltantes a la tabla evaluacion_desempeno
-- ED-01: Debe registrarse fecha, evaluador y criterios usados
-- ED-04: La evaluación debe estar vinculada a un contrato vigente

-- Agregar columnas nuevas si no existen
ALTER TABLE evaluacion_desempeno
ADD COLUMN IF NOT EXISTS id_evaluador UUID NOT NULL,
ADD COLUMN IF NOT EXISTS id_contrato UUID NOT NULL,
ADD COLUMN IF NOT EXISTS criterios VARCHAR(1000) NOT NULL;

-- Agregar foreign keys
ALTER TABLE evaluacion_desempeno
ADD CONSTRAINT fk_evaluacion_evaluador
FOREIGN KEY (id_evaluador) REFERENCES usuario(id);

ALTER TABLE evaluacion_desempeno
ADD CONSTRAINT fk_evaluacion_contrato
FOREIGN KEY (id_contrato) REFERENCES contrato(id);

-- Agregar comentarios para documentar las reglas de negocio
COMMENT ON COLUMN evaluacion_desempeno.fecha IS 'ED-03: La fecha de evaluación no puede ser futura';
COMMENT ON COLUMN evaluacion_desempeno.id_evaluador IS 'ED-01: Usuario que realiza la evaluación';
COMMENT ON COLUMN evaluacion_desempeno.criterios IS 'ED-01: Criterios utilizados para la evaluación (mínimo 10, máximo 1000 caracteres)';
COMMENT ON COLUMN evaluacion_desempeno.id_contrato IS 'ED-04: Contrato vigente al que está vinculada la evaluación';

-- Crear índice para mejorar el rendimiento de la validación ED-02
-- (No se pueden registrar dos evaluaciones en la misma fecha para el mismo usuario)
CREATE UNIQUE INDEX IF NOT EXISTS idx_evaluacion_usuario_fecha
ON evaluacion_desempeno(id_usuario, fecha);
