ALTER TABLE public.transition
ADD name character varying(200) NOT NULL;
ALTER TABLE public.node
ADD position character varying(200) NOT NULL;