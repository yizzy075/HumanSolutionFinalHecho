-- Table: public.puesto

-- DROP TABLE IF EXISTS public.puesto;

CREATE TABLE IF NOT EXISTS public.puesto
(
    id uuid NOT NULL,
    nombre character varying(50) COLLATE pg_catalog."default" NOT NULL,
    estado_id uuid NOT NULL,
    jefe_id uuid,
    unidad_id uuid NOT NULL,
    usuario_id uuid,
    CONSTRAINT puesto_pkey PRIMARY KEY (id),
    CONSTRAINT ukk09u890xcxap4dfqb80r8wjo UNIQUE (nombre, unidad_id),
    CONSTRAINT fk58es680ekyrcjkj9sau46gknx FOREIGN KEY (unidad_id)
        REFERENCES public.unidad_organizativa (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkf07n85wj68du4aw5rk2sfbwo9 FOREIGN KEY (estado_id)
        REFERENCES public.estado_puesto (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkfssk6trypbuhgu5elmry3kpqw FOREIGN KEY (usuario_id)
        REFERENCES public.usuario (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkjwpj0by9afdcha0iqwbd2tj0i FOREIGN KEY (jefe_id)
        REFERENCES public.puesto (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.puesto
    OWNER to postgres;