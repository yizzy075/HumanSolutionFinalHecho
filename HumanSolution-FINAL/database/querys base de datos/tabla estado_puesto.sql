-- Table: public.estado_puesto

-- DROP TABLE IF EXISTS public.estado_puesto;

CREATE TABLE IF NOT EXISTS public.estado_puesto
(
    id uuid NOT NULL,
    nombre character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT estado_puesto_pkey PRIMARY KEY (id),
    CONSTRAINT uk_ser98p6ff3cubs29ivio0aafx UNIQUE (nombre)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.estado_puesto
    OWNER to postgres;