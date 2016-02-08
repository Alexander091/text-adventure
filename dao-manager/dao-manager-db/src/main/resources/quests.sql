INSERT INTO public.quest(id, agelimit, description, genre, name, rating, version, start_node, image_path)
 VALUES(123, 12, 'Первый тестовый квест, загруженный из базы данных',
  'Фэнтези', 'Тестовый квест', 5.0, 1, NULL, '/resources/images/arrow.png');

INSERT INTO public.quest(id, agelimit, description, genre, name, rating, version, start_node, image_path)
 VALUES(1, 12, 'Don''t Panic! Relax, because everything you need to know about playing The Hitchhiker''s Guide to the Galaxy is contained in the pages of this manual. In this story, you will be Arthur Dent, a rather ordinary earth creature who gets swept up in a whirlwind of interstellar adventures almost beyond comprehension. 

As the story begins bulldozers are waiting to reduce your house to rubble to make way for a motorway bypass. While you attempt to deal with this problem, your rather strange friend Ford Prefect drops by to tell you that the Earth is about to be demolished to make way for an interstellar bypass! If you survive this double threat, you''ll embark on a series of inter-galactic misadventures even funnier than your worst nightmares! 

A special note for people who have read the book "The Hitchhiker''s Guide to the Galaxy". Although the opening of the game is fairly similar to the book, the story quickly diverges, with lots of new material and different twists. Although familiarity with the story may make a few of the early puzzles easier, if you rely too heavily on this previous knowledge you will certainly end up getting misled.',
  'Фэнтези', 'The Hitchhiker''s Guide to the Galaxy', 75.0, 1, NULL, '/resources/images/Hitchhiker.jpg');

INSERT INTO public.quest(id, agelimit, description, genre, name, rating, version, start_node, image_path)
 VALUES(2, 12, 'You''re a great detective living in Victorian London. Your internal monologue will guide you by clicking on links in the body of text as you investigate a seemingly average mugging. 

Your eidetic memory is represented by your ability to reread all the story you''ve experienced... 

Your intensely fast analyzing ability is represented by your unlimited time between choices... 

Your vast knowledge is represented by the internet... 

London needs you!',
  'Детектив', 'Victorian detective', 87.0, 1, NULL, '/resources/images/Victorian_detective.jpg');

  INSERT INTO public.quest(id, agelimit, description, genre, name, rating, version, start_node, image_path)
 VALUES(3, 14, 'A half-true game about half-truths. You play as a semi-fictional version of me, on a night that changed my life forever. Choose your (my?) words wisely. Every character will remember everything you say - or don''t say - as you figure out how to approach my (your?) hyper-conservative Asian parents. And if all that seems confusing or awkward... well, that''s the gist of coming out as queer, isn''t it? ',
  'Slice of life', 'Coming Out Simulator', 25.4, 1, NULL, '/resources/images/coming_out.png');

  INSERT INTO public.quest(id, agelimit, description, genre, name, rating, version, start_node, image_path)
 VALUES(4, 12, 'he year is 2050. 

You are the captain of SC Denver; a Star Command task vessel with the job of keeping everyone safe and carrying the will of the Admiral. 

It''s not easy. 

Just remember, you are the captain. Your choices effect everyone on-board and everyone around you. Sooner or later you will come to regret or be rewarded by your choices. 

*DEV NOTE* 

As of current, the game doesn''t feature many variations - many choices lead to the same result as you may notice. When the storyline is complete I will work on that! Enjoy ;) 

0.3 A bit more!',
  'Sci-fi', 'Star Command!', 92.4, 0.3, NULL, '/resources/images/star_command.jpg');

    INSERT INTO public.quest(id, agelimit, description, genre, name, rating, version, start_node, image_path)
 VALUES(5, 12, 'You are a Stormtrooper who has been captured during a mission on a rebel flagship. You are currently awaiting execution; can you escape and get to the escape pods? 

Play this game multiple times. There are much more than a single ending for each Chapter. Just check the achievement list to see how many paths there are for each chapter. Though there is not an achievement for every single path. 

This is a message for Titanfield. Stormtroopers are not clones, and it is painful to see you give a 2 star rating for a reason you were misinformed about. :( ',
  'Фэнтези', 'Escape from tower', 42.0, 2, NULL, '/resources/images/Escape.jpg');

--INSERT INTO resource (id, name, path, quest_id, type_id) VALUES (1, 'coming out simulator 2014', '/resources/images/coming_out.png', 3, 1);
--INSERT INTO resource (id, name, path, quest_id, type_id) VALUES (2, 'Hitchhiker', '/resources/images/Hitchhiker.jpg', 1, 1);
--INSERT INTO resource (id, name, path, quest_id, type_id) VALUES (3, 'Victorian_detective', '/resources/images/Victorian_detective.jpg', 2, 1);
--INSERT INTO resource (id, name, path, quest_id, type_id) VALUES (4, 'Placeholder', '/resources/images/arrow.png', 123, 1);

-- UPDATE quest
--    SET image_resource=4
--  WHERE id = 123;
-- 
--  UPDATE quest
--    SET image_resource=2
--  WHERE id = 1;
-- 
--  UPDATE quest
--    SET image_resource=3
--  WHERE id = 2;
-- 
--  UPDATE quest
--    SET image_resource=1
--  WHERE id = 3;
-- 
--  UPDATE quest
--    SET image_resource=4
--  WHERE id = 4;
-- 
--  UPDATE quest
--    SET image_resource=4
--  WHERE id = 5;