-- Table: public.estado_solicitud

-- DROP TABLE IF EXISTS public.estado_solicitud;

CREATE TABLE IF NOT EXISTS public.estado_solicitud
(
    id uuid NOT NULL,
    nombre character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT estado_solicitud_pkey PRIMARY KEY (id),
    CONSTRAINT uk_fos4ihu5q817sjmhvcwu1mjb9 UNIQUE (nombre)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.estado_solicitud
    OWNER to postgres;