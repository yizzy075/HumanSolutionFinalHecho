-- Table: public.evaluacion_desempeno

-- DROP TABLE IF EXISTS public.evaluacion_desempeno;

CREATE TABLE IF NOT EXISTS public.evaluacion_desempeno
(
    id uuid NOT NULL,
    id_usuario uuid NOT NULL,
    fecha date NOT NULL,
    calificacion integer NOT NULL,
    observacion character varying(500) COLLATE pg_catalog."default" NOT NULL,
    criterios character varying(1000) COLLATE pg_catalog."default" NOT NULL,
    id_contrato uuid NOT NULL,
    id_evaluador uuid NOT NULL,
    CONSTRAINT evaluacion_desempeno_pkey PRIMARY KEY (id),
    CONSTRAINT unique_usuario_fecha UNIQUE (id_usuario, fecha),
    CONSTRAINT fk_evaluacion_usuario FOREIGN KEY (id_usuario)
        REFERENCES public.usuario (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE RESTRICT,
    CONSTRAINT chk_calificacion_rango CHECK (calificacion >= 1 AND calificacion <= 10),
    CONSTRAINT chk_fecha_no_futura CHECK (fecha <= CURRENT_DATE),
    CONSTRAINT chk_observacion_longitud CHECK (length(observacion::text) >= 5 AND length(observacion::text) <= 500)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.evaluacion_desempeno
    OWNER to postgres;

COMMENT ON TABLE public.evaluacion_desempeno
    IS 'Almacena las evaluaciones de desempeño de los usuarios';

COMMENT ON COLUMN public.evaluacion_desempeno.id
    IS 'Identificador único de la evaluación (UUID)';

COMMENT ON COLUMN public.evaluacion_desempeno.id_usuario
    IS 'Referencia al usuario evaluado';

COMMENT ON COLUMN public.evaluacion_desempeno.fecha
    IS 'Fecha de la evaluación (no puede ser futura)';

COMMENT ON COLUMN public.evaluacion_desempeno.calificacion
    IS 'Calificación del desempeño (1-10)';

COMMENT ON COLUMN public.evaluacion_desempeno.observacion
    IS 'Observaciones de la evaluación (5-500 caracteres)';
-- Index: idx_evaluacion_fecha

-- DROP INDEX IF EXISTS public.idx_evaluacion_fecha;

CREATE INDEX IF NOT EXISTS idx_evaluacion_fecha
    ON public.evaluacion_desempeno USING btree
    (fecha ASC NULLS LAST)
    TABLESPACE pg_default;
-- Index: idx_evaluacion_usuario

-- DROP INDEX IF EXISTS public.idx_evaluacion_usuario;

CREATE INDEX IF NOT EXISTS idx_evaluacion_usuario
    ON public.evaluacion_desempeno USING btree
    (id_usuario ASC NULLS LAST)
    TABLESPACE pg_default;