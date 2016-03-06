CREATE TABLE public.action
(
  id bigint NOT NULL,
  condition character varying(200) NOT NULL,
  value real,
  item bigint,
  node bigint,
  parent bigint,
  resource bigint,
  stat bigint,
  type_id bigint NOT NULL,
  CONSTRAINT action_pkey PRIMARY KEY (id)
);

CREATE TABLE public.game
(
  id bigint NOT NULL,
  quest_id bigint,
  user_id bigint,
  CONSTRAINT game_pkey PRIMARY KEY (id)
);

CREATE TABLE public.item
(
  id bigint NOT NULL,
  description character varying(2000),
  name character varying(200) NOT NULL,
  quest_id bigint,
  CONSTRAINT item_pkey PRIMARY KEY (id)
);

CREATE TABLE public.node
(
  id bigint NOT NULL,
  description character varying(2000),
  name character varying(200) NOT NULL,
  quest_id bigint NOT NULL,
  CONSTRAINT node_pkey PRIMARY KEY (id)
);

CREATE TABLE public.quest
(
  id bigint NOT NULL,
  agelimit integer,
  description character varying(2000),
  genre character varying(100) NOT NULL,
  name character varying(200) NOT NULL,
  rating real,
  version integer,
  start_node bigint,
  CONSTRAINT quest_pkey PRIMARY KEY (id)
);

CREATE TABLE public.resource
(
  id bigint NOT NULL,
  name character varying(200) NOT NULL,
  path character varying(1000) NOT NULL,
  quest_id bigint NOT NULL,
  type_id bigint NOT NULL,
  CONSTRAINT resource_pkey PRIMARY KEY (id)
);

CREATE TABLE public.saved_item
(
  id bigint NOT NULL,
  game_id bigint NOT NULL,
  item_id bigint NOT NULL,
  CONSTRAINT saved_item_pkey PRIMARY KEY (id)
);

CREATE TABLE public.saved_stat
(
  id bigint NOT NULL,
  value real NOT NULL,
  game_id bigint NOT NULL,
  stat_id bigint,
  CONSTRAINT saved_stat_pkey PRIMARY KEY (id)
);

CREATE TABLE public.stat
(
  id bigint NOT NULL,
  description character varying(2000),
  name character varying(200) NOT NULL,
  value real,
  quest_id bigint NOT NULL,
  CONSTRAINT stat_pkey PRIMARY KEY (id)
);

CREATE TABLE public.transition
(
  id bigint NOT NULL,
  condition character varying(1000) NOT NULL,
  from_node bigint NOT NULL,
  to_node bigint NOT NULL,
  CONSTRAINT transition_pkey PRIMARY KEY (id)
);

CREATE TABLE public.type_of_action
(
  id bigint NOT NULL,
  name character varying(100) NOT NULL,
  CONSTRAINT type_of_action_pkey PRIMARY KEY (id)
);

CREATE TABLE public.type_of_resource
(
  id bigint NOT NULL,
  name character varying(100) NOT NULL,
  CONSTRAINT type_of_resource_pkey PRIMARY KEY (id)
);

CREATE TABLE public."user"
(
  id bigint NOT NULL,
  email character varying(200) NOT NULL,
  log_name character varying(100) NOT NULL,
  name character varying(100),
  password character varying(200) NOT NULL,
  surname character varying(200),
  user_role bigint,
  CONSTRAINT user_pkey PRIMARY KEY (id)
);

CREATE TABLE public.user_role
(
  id bigint NOT NULL,
  name character varying(200) NOT NULL,
  CONSTRAINT user_role_pkey PRIMARY KEY (id)
);

ALTER TABLE public.action 
	ADD FOREIGN KEY (type_id) REFERENCES public.type_of_action (id);
ALTER TABLE public.action
	ADD FOREIGN KEY (resource) REFERENCES public.resource (id);
ALTER TABLE public.action
	ADD FOREIGN KEY (stat) REFERENCES public.stat (id);
ALTER TABLE public.action
	ADD FOREIGN KEY (node) REFERENCES public.node (id);
ALTER TABLE public.action
	ADD FOREIGN KEY (parent) REFERENCES public.action (id);
ALTER TABLE public.action
	ADD FOREIGN KEY (item) REFERENCES public.item (id);

ALTER TABLE public.game 
	ADD FOREIGN KEY (quest_id) REFERENCES public.quest (id);
ALTER TABLE public.game
	ADD FOREIGN KEY (user_id) REFERENCES public."user" (id);

ALTER TABLE public.item 
	ADD FOREIGN KEY (quest_id) REFERENCES public.quest (id);

ALTER TABLE public.node 
	ADD FOREIGN KEY (quest_id) REFERENCES public.quest (id);
	
ALTER TABLE public.quest 
	ADD FOREIGN KEY (start_node) REFERENCES public.node (id);

ALTER TABLE public.resource 
	ADD FOREIGN KEY (quest_id) REFERENCES public.quest (id);
ALTER TABLE public.resource
	ADD FOREIGN KEY (type_id) REFERENCES public.type_of_resource (id);
	
ALTER TABLE public.saved_item 
	ADD FOREIGN KEY (game_id) REFERENCES public.game (id);
ALTER TABLE public.saved_item
	ADD FOREIGN KEY (item_id) REFERENCES public.item (id);
	
ALTER TABLE public.saved_stat 
	ADD FOREIGN KEY (stat_id) REFERENCES public.stat (id);
ALTER TABLE public.saved_stat
	ADD FOREIGN KEY (game_id) REFERENCES public.game (id);
	
ALTER TABLE public.stat 
	ADD FOREIGN KEY (quest_id) REFERENCES public.quest (id);
	
ALTER TABLE public.transition
  ADD CONSTRAINT trans_to_node_f_key
	FOREIGN KEY (to_node) REFERENCES public.node (id) ON DELETE CASCADE;
ALTER TABLE public.transition
  ADD CONSTRAINT trans_from_node_f_key
  FOREIGN KEY (from_node) REFERENCES public.node (id) ON DELETE CASCADE;
	
ALTER TABLE public."user" 
	ADD FOREIGN KEY (user_role) REFERENCES public.user_role (id);