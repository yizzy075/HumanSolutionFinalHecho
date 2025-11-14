-- Table: public.rol_permiso

-- DROP TABLE IF EXISTS public.rol_permiso;

CREATE TABLE IF NOT EXISTS public.rol_permiso
(
    id uuid NOT NULL,
    permiso_id uuid NOT NULL,
    rol_id uuid NOT NULL,
    CONSTRAINT rol_permiso_pkey PRIMARY KEY (id),
    CONSTRAINT ukdc48a4bh4fdxkq87y19uegc6w UNIQUE (rol_id, permiso_id),
    CONSTRAINT fk6o522368i97la9m9cqn0gul2e FOREIGN KEY (rol_id)
        REFERENCES public.rol (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkl9x5exstff683cbrcl28hj0q6 FOREIGN KEY (permiso_id)
        REFERENCES public.permiso_sistema (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fksfps2dfuosroh7vg5bihe13y1 FOREIGN KEY (rol_id)
        REFERENCES public.roles (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.rol_permiso
    OWNER to postgres;