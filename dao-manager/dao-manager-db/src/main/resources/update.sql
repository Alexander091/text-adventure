ALTER TABLE public.transition
ADD name character varying(200) NOT NULL;
ALTER TABLE public.node
ADD position character varying(200) NOT NULL;
-- ALTER TABLE public.node
-- 	ADD FOREIGN KEY (quest_id) REFERENCES public.quest (id) ON DELETE CASCADE;
-- ALTER TABLE public.quest
-- 	ADD FOREIGN KEY (start_node) REFERENCES public.node (id) ON DELETE CASCADE;
ALTER TABLE public.transition
  ADD CONSTRAINT trans_to_node_f_key
	FOREIGN KEY (to_node) REFERENCES public.node (id) ON DELETE CASCADE;
ALTER TABLE public.transition
  ADD CONSTRAINT trans_from_node_f_key
  FOREIGN KEY (from_node) REFERENCES public.node (id) ON DELETE CASCADE;
