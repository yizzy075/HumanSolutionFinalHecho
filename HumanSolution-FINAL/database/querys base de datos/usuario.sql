-- Table: public.usuario

-- DROP TABLE IF EXISTS public.usuario;

CREATE TABLE IF NOT EXISTS public.usuario
(
    id uuid NOT NULL,
    activo boolean NOT NULL,
    apellido character varying(50) COLLATE pg_catalog."default" NOT NULL,
    contrasena character varying(100) COLLATE pg_catalog."default" NOT NULL,
    correo character varying(100) COLLATE pg_catalog."default" NOT NULL,
    fecha_actualizacion timestamp(6) without time zone,
    fecha_creacion timestamp(6) without time zone,
    nombre character varying(50) COLLATE pg_catalog."default" NOT NULL,
    numero_documento character varying(15) COLLATE pg_catalog."default" NOT NULL,
    rol character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT usuarios_pkey PRIMARY KEY (id),
    CONSTRAINT uk_cdmw5hxlfj78uf4997i3qyyw5 UNIQUE (correo),
    CONSTRAINT uk_r8iybs7gfuarh9nyn274v6vjk UNIQUE (numero_documento)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.usuario
    OWNER to postgres;