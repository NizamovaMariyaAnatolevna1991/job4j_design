
insert into roles (name) values ('admin');
insert into roles (name) values ('user');
insert into roles (name) values ('moderator');

insert into users (name, user_id) values
('Алексей', 1),
('Мария', 2),
('Иван', 3);

insert into rules (name) values
('create_item'),
('edit_own_item'),
('delete_own_item'),
('edit_all_items'),
('delete_all_items'),
('add_comment'),
('add_attach');

insert into roles_rules (role_id, rule_id) values
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), 
(2, 1), (2, 2), (2, 3), (2, 6), (2, 7),                 
(3, 4), (3, 5), (3, 6);   	

insert into categories (name) values
('Техническая проблема'),
('Заявка на обслуживание'),
('Ошибка приложения'),
('Обратная связь');

insert into states (name) values
('Новая'),
('В работе'),
('Завершена'),
('Отменена');

insert into items (name, users_id, category_id, state_id) values
('Не работает интернет', 1, 1, 2),
('Ошибка входа в систему', 2, 3, 1),
('Замена принтера', 3, 2, 3);

insert into comments (name, item_id) values
('Проверьте кабель.', 1),
('Проблема воспроизведена.', 2),
('Выполнено.', 3);

insert into attachs (name, item_id) values
('log_file.txt', 2),
('photo_of_issue.jpg', 3);















