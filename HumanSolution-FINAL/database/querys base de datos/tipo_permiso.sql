-- Table: public.tipo_permiso

-- DROP TABLE IF EXISTS public.tipo_permiso;

CREATE TABLE IF NOT EXISTS public.tipo_permiso
(
    id uuid NOT NULL,
    descripcion character varying(200) COLLATE pg_catalog."default" NOT NULL,
    nombre character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT tipo_permiso_pkey PRIMARY KEY (id),
    CONSTRAINT uk_5nd9614fyjfaq5aqmsr25igx1 UNIQUE (nombre)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.tipo_permiso
    OWNER to postgres;