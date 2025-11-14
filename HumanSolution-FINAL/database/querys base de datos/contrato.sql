-- Table: public.contrato

-- DROP TABLE IF EXISTS public.contrato;

CREATE TABLE IF NOT EXISTS public.contrato
(
    id uuid NOT NULL,
    id_usuario uuid NOT NULL,
    fecha_inicio date NOT NULL,
    fecha_fin date,
    sueldo numeric(38,2) NOT NULL,
    CONSTRAINT contrato_pkey PRIMARY KEY (id),
    CONSTRAINT fk_contrato_usuario FOREIGN KEY (id_usuario)
        REFERENCES public.usuario (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT chk_contrato_fechas CHECK (fecha_fin IS NULL OR fecha_fin >= fecha_inicio),
    CONSTRAINT chk_contrato_sueldo_positivo CHECK (sueldo > 0::numeric)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.contrato
    OWNER to postgres;