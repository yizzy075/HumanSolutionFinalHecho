-- Table: public.tipo_documento

-- DROP TABLE IF EXISTS public.tipo_documento;

CREATE TABLE IF NOT EXISTS public.tipo_documento
(
    id uuid NOT NULL,
    descripcion character varying(200) COLLATE pg_catalog."default" NOT NULL,
    nombre character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT tipo_documento_pkey PRIMARY KEY (id),
    CONSTRAINT uk_emy1oj414hmbw18ng7204ean2 UNIQUE (nombre)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.tipo_documento
    OWNER to postgres;