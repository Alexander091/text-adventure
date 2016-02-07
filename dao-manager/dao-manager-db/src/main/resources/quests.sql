INSERT INTO public.quest(id, agelimit, description, genre, name, rating, version, start_node, image_resource)
 VALUES(123, 12, 'Первый тестовый квест, загруженный из базы данных',
  'Фэнтези', 'Тестовый квест', 5.0, 1, NULL, 4);

INSERT INTO public.quest(id, agelimit, description, genre, name, rating, version, start_node, image_resource)
 VALUES(1, 12, 'Don''t Panic! Relax, because everything you need to know about playing The Hitchhiker''s Guide to the Galaxy is contained in the pages of this manual. In this story, you will be Arthur Dent, a rather ordinary earth creature who gets swept up in a whirlwind of interstellar adventures almost beyond comprehension. 

As the story begins bulldozers are waiting to reduce your house to rubble to make way for a motorway bypass. While you attempt to deal with this problem, your rather strange friend Ford Prefect drops by to tell you that the Earth is about to be demolished to make way for an interstellar bypass! If you survive this double threat, you''ll embark on a series of inter-galactic misadventures even funnier than your worst nightmares! 

A special note for people who have read the book "The Hitchhiker''s Guide to the Galaxy". Although the opening of the game is fairly similar to the book, the story quickly diverges, with lots of new material and different twists. Although familiarity with the story may make a few of the early puzzles easier, if you rely too heavily on this previous knowledge you will certainly end up getting misled.',
  'Фэнтези', 'The Hitchhiker''s Guide to the Galaxy', 75.0, 1, NULL, 2);

INSERT INTO public.quest(id, agelimit, description, genre, name, rating, version, start_node, image_resource)
 VALUES(2, 12, 'You''re a great detective living in Victorian London. Your internal monologue will guide you by clicking on links in the body of text as you investigate a seemingly average mugging. 

Your eidetic memory is represented by your ability to reread all the story you''ve experienced... 

Your intensely fast analyzing ability is represented by your unlimited time between choices... 

Your vast knowledge is represented by the internet... 

London needs you!',
  'Детектив', 'Victorian detective', 87.0, 1, NULL, 3);

  INSERT INTO public.quest(id, agelimit, description, genre, name, rating, version, start_node, image_resource)
 VALUES(3, 14, 'A half-true game about half-truths. You play as a semi-fictional version of me, on a night that changed my life forever. Choose your (my?) words wisely. Every character will remember everything you say - or don''t say - as you figure out how to approach my (your?) hyper-conservative Asian parents. And if all that seems confusing or awkward... well, that''s the gist of coming out as queer, isn''t it? ',
  'Slice of life', 'Coming Out Simulator', 25.4, 1, NULL, 1);

  INSERT INTO public.quest(id, agelimit, description, genre, name, rating, version, start_node, image_resource)
 VALUES(4, 12, 'Найдите королеву!',
  'Фэнтези', 'Find the queen!', 42.0, 2, NULL, 4);

    INSERT INTO public.quest(id, agelimit, description, genre, name, rating, version, start_node, image_resource)
 VALUES(5, 12, 'Совершите дерзкий побег!',
  'Фэнтези', 'Escape from tower', 42.0, 2, NULL, 4);



INSERT INTO resource (id, name, path, quest_id, type_id) VALUES (1, 'coming out simulator 2014', '/resources/images/coming_out.png', 3, 1);
INSERT INTO resource (id, name, path, quest_id, type_id) VALUES (2, 'Hitchhiker', '/resources/images/Hitchhiker.jpg', 1, 1);
INSERT INTO resource (id, name, path, quest_id, type_id) VALUES (3, 'Victorian_detective', '/resources/images/Victorian_detective.jpg', 2, 1);
INSERT INTO resource (id, name, path, quest_id, type_id) VALUES (4, 'Placeholder', '/resources/images/arrow.png', 123, 1);
  