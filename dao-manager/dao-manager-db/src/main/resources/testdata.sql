INSERT INTO public.quest(id, agelimit, description, genre, name,
rating, version, start_node)
 VALUES(123, 12, '������ �������� �����, ����������� �� ���� ������',
  '�������', '�������� �����', 5.0, 1, NULL);



INSERT INTO public.node(id, description, name, quest_id, position)
 VALUES(1, '���, ����� ���',
  '���', 123, '250 125');

INSERT INTO public.node(id, description, name, quest_id, position)
 VALUES(2, '�������� ����� ��������� �� ����� ���� �����...',
  '���', 123, '125 250');

 INSERT INTO public.node(id, description, name, quest_id, position)
 VALUES(3, '������ ���������� ������� �� ���� ������',
  '������', 123, '375 250');

 INSERT INTO public.node(id, description, name, quest_id, position)
 VALUES(4, '�� ������ ��� ��������� � ��������! �����������!',
  '�����', 123, '250 375');

 INSERT INTO public.transition(id, condition, name, from_node, to_node)
 VALUES(1, '����', '�������� � ���', 1, 2);

  INSERT INTO public.transition(id, condition, name, from_node, to_node)
 VALUES(2, '����', '���� � ������', 1, 3);

  INSERT INTO public.transition(id, condition, name, from_node, to_node)
 VALUES(3, '����', '������� � ������', 2, 4);

   INSERT INTO public.transition(id, condition, name, from_node, to_node)
 VALUES(4, '����', '������� � ������', 3, 4);

   INSERT INTO public.transition(id, condition, name, from_node, to_node)
 VALUES(5, '����', '����� �� ���� � ������', 2, 3);

   INSERT INTO public.transition(id, condition, name, from_node, to_node)
 VALUES(6, '����', '��������� � ���', 3, 1);

UPDATE public.quest
SET start_node = 1
where id = 123;