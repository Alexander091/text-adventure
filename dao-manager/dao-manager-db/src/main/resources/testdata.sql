INSERT INTO public.quest(id, agelimit, description, genre, name,
rating, version, start_node)
 VALUES(123, 12, 'Первый тестовый квест, загруженный из базы данных',
  'Фэнтези', 'Тестовый квест', 5.0, 1, NULL);



INSERT INTO public.node(id, description, name, quest_id, position)
 VALUES(1, 'Дом, милый дом',
  'Дом', 123, '250 125');

INSERT INTO public.node(id, description, name, quest_id, position)
 VALUES(2, 'Странные звуки доносятся из этого леса ночью...',
  'Лес', 123, '125 250');

 INSERT INTO public.node(id, description, name, quest_id, position)
 VALUES(3, 'Немало странников пропало на этой дороге',
  'Дорога', 123, '375 250');

 INSERT INTO public.node(id, description, name, quest_id, position)
 VALUES(4, 'Вы прошли все трудности и победили! Поздравляем!',
  'Финал', 123, '250 375');

 INSERT INTO public.transition(id, condition, name, from_node, to_node)
 VALUES(1, 'Тест', 'Пройтись в лес', 1, 2);

  INSERT INTO public.transition(id, condition, name, from_node, to_node)
 VALUES(2, 'Тест', 'Идти к дороге', 1, 3);

  INSERT INTO public.transition(id, condition, name, from_node, to_node)
 VALUES(3, 'Тест', 'Перейти к финалу', 2, 4);

   INSERT INTO public.transition(id, condition, name, from_node, to_node)
 VALUES(4, 'Тест', 'Перейти к финалу', 3, 4);

   INSERT INTO public.transition(id, condition, name, from_node, to_node)
 VALUES(5, 'Тест', 'Выйти из леса к дороге', 2, 3);

   INSERT INTO public.transition(id, condition, name, from_node, to_node)
 VALUES(6, 'Тест', 'Вернуться в дом', 3, 1);

UPDATE public.quest
SET start_node = 1
where id = 123;