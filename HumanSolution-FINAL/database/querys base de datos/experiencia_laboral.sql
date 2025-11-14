-- Table: public.experiencia_laboral

-- DROP TABLE IF EXISTS public.experiencia_laboral;

CREATE TABLE IF NOT EXISTS public.experiencia_laboral
(
    id uuid NOT NULL,
    cargo character varying(200) COLLATE pg_catalog."default" NOT NULL,
    empresa character varying(50) COLLATE pg_catalog."default" NOT NULL,
    fecha_fin date,
    fecha_inicio date NOT NULL,
    usuario_id uuid NOT NULL,
    CONSTRAINT experiencia_laboral_pkey PRIMARY KEY (id),
    CONSTRAINT uk1orp2seyr4rpylgsqlp448pee UNIQUE (usuario_id, empresa, cargo, fecha_inicio),
    CONSTRAINT fk8kfoamisqwr5s2bv2cmo6se8d FOREIGN KEY (usuario_id)
        REFERENCES public.usuario (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.experiencia_laboral
    OWNER to postgres;