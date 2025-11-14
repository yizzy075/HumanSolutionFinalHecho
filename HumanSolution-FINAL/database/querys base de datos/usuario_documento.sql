-- Table: public.usuario_documento

-- DROP TABLE IF EXISTS public.usuario_documento;

CREATE TABLE IF NOT EXISTS public.usuario_documento
(
    id uuid NOT NULL,
    descripcion character varying(500) COLLATE pg_catalog."default",
    fecha date NOT NULL,
    estado_solicitud_id uuid NOT NULL,
    tipo_documento_id uuid NOT NULL,
    usuario_id uuid NOT NULL,
    CONSTRAINT usuario_documento_pkey PRIMARY KEY (id),
    CONSTRAINT uk2vfsjnctr99im44ax846c6v3t UNIQUE (usuario_id, tipo_documento_id, fecha),
    CONSTRAINT fk2347ingg98b0e7svij5s8lu6i FOREIGN KEY (estado_solicitud_id)
        REFERENCES public.estado_solicitud (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk6o53h89q0l1ncsbe3ml2k9hls FOREIGN KEY (usuario_id)
        REFERENCES public.usuario (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fksv12qpqh0qpbt77ob5f8yymnd FOREIGN KEY (tipo_documento_id)
        REFERENCES public.tipo_documento (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.usuario_documento
    OWNER to postgres;