-- Table: public.usuario_hora_extra

-- DROP TABLE IF EXISTS public.usuario_hora_extra;

CREATE TABLE IF NOT EXISTS public.usuario_hora_extra
(
    id uuid NOT NULL,
    fecha date NOT NULL,
    horas integer NOT NULL,
    estado_solicitud_id uuid NOT NULL,
    tipo_hora_extra_id uuid NOT NULL,
    usuario_id uuid NOT NULL,
    CONSTRAINT usuario_hora_extra_pkey PRIMARY KEY (id),
    CONSTRAINT ukmhillk08ivtdrs0l142nd27nk UNIQUE (usuario_id, fecha, tipo_hora_extra_id),
    CONSTRAINT fkgcx6qrae2tl3swwmxvdj6cnkw FOREIGN KEY (tipo_hora_extra_id)
        REFERENCES public.tipo_hora_extra (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkl1h39m3nee85w27y1gl7m36rd FOREIGN KEY (estado_solicitud_id)
        REFERENCES public.estado_solicitud (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fks49g8kfv47i3hqtutqpcsi7u FOREIGN KEY (usuario_id)
        REFERENCES public.usuario (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.usuario_hora_extra
    OWNER to postgres;