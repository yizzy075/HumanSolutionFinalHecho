-- Table: public.permiso_sistema

-- DROP TABLE IF EXISTS public.permiso_sistema;

CREATE TABLE IF NOT EXISTS public.permiso_sistema
(
    id uuid NOT NULL,
    descripcion character varying(200) COLLATE pg_catalog."default",
    nombre character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT permiso_sistema_pkey PRIMARY KEY (id),
    CONSTRAINT uk_7ktg8iunnmeoyrfvfwfoibpnf UNIQUE (nombre)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.permiso_sistema
    OWNER to postgres;