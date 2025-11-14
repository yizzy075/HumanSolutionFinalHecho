-- Table: public.tipo_hora_extra

-- DROP TABLE IF EXISTS public.tipo_hora_extra;

CREATE TABLE IF NOT EXISTS public.tipo_hora_extra
(
    id uuid NOT NULL,
    nombre character varying(50) COLLATE pg_catalog."default" NOT NULL,
    recargo integer NOT NULL,
    CONSTRAINT tipo_hora_extra_pkey PRIMARY KEY (id),
    CONSTRAINT uk_fvedq7hr80b5nibamo69soi84 UNIQUE (nombre)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.tipo_hora_extra
    OWNER to postgres;