-- Table: public.usuario_permiso

-- DROP TABLE IF EXISTS public.usuario_permiso;

CREATE TABLE IF NOT EXISTS public.usuario_permiso
(
    id uuid NOT NULL,
    fecha_fin date NOT NULL,
    fecha_inicio date NOT NULL,
    observacion character varying(500) COLLATE pg_catalog."default",
    estado_solicitud_id uuid NOT NULL,
    tipo_permiso_id uuid NOT NULL,
    usuario_id uuid NOT NULL,
    CONSTRAINT usuario_permiso_pkey PRIMARY KEY (id),
    CONSTRAINT uklvhxiwgoop5sbbbwslerqbhr1 UNIQUE (usuario_id, tipo_permiso_id, fecha_inicio, fecha_fin),
    CONSTRAINT fk8htqmam2mioewibplxpw6ws58 FOREIGN KEY (estado_solicitud_id)
        REFERENCES public.estado_solicitud (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkscuy48tq7mg0lyg42jks45gpv FOREIGN KEY (tipo_permiso_id)
        REFERENCES public.tipo_permiso (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fksg8oy71i72gcbenr26sne4k7r FOREIGN KEY (usuario_id)
        REFERENCES public.usuario (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.usuario_permiso
    OWNER to postgres;