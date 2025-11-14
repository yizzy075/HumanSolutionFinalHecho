-- Table: public.unidad_organizativa

-- DROP TABLE IF EXISTS public.unidad_organizativa;

CREATE TABLE IF NOT EXISTS public.unidad_organizativa
(
    id uuid NOT NULL,
    nombre character varying(200) COLLATE pg_catalog."default" NOT NULL,
    unidad_superior_id uuid,
    CONSTRAINT unidad_organizativa_pkey PRIMARY KEY (id),
    CONSTRAINT uk_gworwwm6y27fxw2xgi9mfpi1i UNIQUE (nombre),
    CONSTRAINT fk5ujykufmsyuxxcrwhrfyoppup FOREIGN KEY (unidad_superior_id)
        REFERENCES public.unidad_organizativa (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.unidad_organizativa
    OWNER to postgres;