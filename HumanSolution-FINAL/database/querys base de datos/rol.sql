-- Table: public.rol

-- DROP TABLE IF EXISTS public.rol;

CREATE TABLE IF NOT EXISTS public.rol
(
    id uuid NOT NULL,
    nombre character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT rol_pkey PRIMARY KEY (id),
    CONSTRAINT uk_43kr6s7bts1wqfv43f7jd87kp UNIQUE (nombre)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.rol
    OWNER to postgres;