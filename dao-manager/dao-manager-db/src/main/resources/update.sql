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

ALTER TABLE resource DROP COLUMN path;
ALTER TABLE resource ADD COLUMN data bytea;
ALTER TABLE action ALTER COLUMN condition DROP NOT NULL;
ALTER TABLE type_of_action ADD COLUMN type_of_resource_id bigint;
ALTER TABLE type_of_action
  ADD CONSTRAINT type_of_resource_fk
   FOREIGN KEY (type_of_resource_id) REFERENCES type_of_resource(id);
insert into type_of_resource values(1,'image');
insert into type_of_resource values(2,'sound');
insert into type_of_action values(1,'Показать картинку',1);
insert into type_of_action values(2,'Проиграть звук',2);
insert into resource values(1,'sound1', 123, 2, NULL);
insert into resource values(2,'sound3', 123, 2, NULL);
insert into resource values(3,'img1', 123, 1, NULL);
insert into resource values(4,'img2', 123, 1, NULL);
