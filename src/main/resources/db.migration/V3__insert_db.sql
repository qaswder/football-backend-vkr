-- Добавление ролей пользователей
INSERT INTO fc_role_user (role_name, description)
VALUES ('ADMIN', 'Администратор системы с полными правами'),
       ('EDITOR', 'Редактор контента с ограниченными правами');

-- Добавление пользователей
INSERT INTO fc_user (username, email, password, user_role_id, created_at, updated_at)
VALUES ('Администратор', 'admin@example.com', '$2a$10$xJwL5v5Jz5U6Z7b5U5XZ3e3Xz5U6Z7b5U5XZ3e3Xz5U6Z7b5U5XZ3',
        1, NOW(), NOW()),
       ('Редактор', 'editor@example.com', '$2a$10$xJwL5v5Jz5U6Z7b5U5XZ3e3Xz5U6Z7b5U5XZ3e3Xz5U6Z7b5U5XZ3', 2,
        NOW(), NOW());

-- Добавление команд
INSERT INTO fc_team (team_name, team_short_name, stadium, logo_url)
VALUES ('Спартак Москва', 'СПА', 'Открытие Арена', 'https://example.com/logos/spartak.png'),
       ('Зенит Санкт-Петербург', 'ЗЕН', 'Газпром Арена', 'https://example.com/logos/zenit.png');

-- Добавление турниров
INSERT INTO fc_tournament (tournament_name, season)
VALUES ('Российская Премьер-Лига', '2023/2024'),
       ('Кубок России', '2023/2024');

-- Добавление персонала
INSERT INTO fc_staff (surname, name, patronymic, birthdate, staff_role, photo_url, team_id)
VALUES ('Абаскаль', 'Гильермо', 'Оскарович', '1973-08-28', 'COACH', 'https://example.com/staff/abascal.jpg', 1),
       ('Семак', 'Сергей', 'Богданович', '1976-02-27', 'AWAIT', 'https://example.com/staff/semak.jpg', 2);

-- Добавление игроков
INSERT INTO fc_player (surname, name, patronymic, birth_date, nationality, player_number, position, photo_url, team_id)
VALUES ('Промес', 'Куинси', 'Антони', '1992-01-04', 'Нидерланды', 10, 'FORWARD',
        'https://example.com/players/promes.jpg', 1),
       ('Малком', 'Малком', 'Фелипе', '1996-02-26', 'Бразилия', 10, 'FORWARD', 'https://example.com/players/malcom.jpg',
        2);

-- Добавление матчей
INSERT INTO fc_match (home_team_id, away_team_id, score, location, match_status, date_time, tournament_id)
VALUES (1, 2, '2:1', 'Открытие Арена', 'AWAIT', '2023-07-15 19:00:00', 1),
       (2, 1, NULL, 'Газпром Арена', 'AWAIT', '2024-04-20 18:00:00', 1);

-- Добавление результатов турниров
INSERT INTO fc_results (position, played, wins, draws, losses, points, team_id, tournament_id)
VALUES (1, 10, 8, 1, 1, 25, 2, 1),
       (2, 10, 7, 2, 1, 23, 1, 1);

-- Добавление статистики игроков
INSERT INTO fc_player_statistics (player_id, match_id, goals, assists, yellow_cards, red_cards, played_minutes, shots,
                                  passes, season)
VALUES (1, 1, 1, 1, 0, 0, 90, 5, 42, '2023-01-01'),
       (2, 1, 0, 0, 1, 0, 90, 3, 38, '2023-01-01');

-- Добавление тестовых новостей
INSERT INTO fc_news (title, content, publication_date, image_url, author_id)
VALUES
    ('Победа в матче против Спартака', 'Наша команда одержала уверенную победу со счетом 3:1...', '2023-10-15 18:30:00', '/images/news1.jpg', 1),
    ('Новый игрок в составе', 'Мы рады представить нашего нового игрока - Ивана Петрова...', '2023-10-10 12:00:00', '/images/news2.jpg', 2),
    ('Подготовка к следующему сезону', 'Команда начала усиленную подготовку к предстоящему сезону...', '2023-09-28 14:15:00', '/images/news3.jpg', 1);

-- Добавление тестовых комментариев к первой новости (id=1)
INSERT INTO fc_comment (content, publication_date, author_id, news_id)
VALUES
    ('Отличная игра! Так держать!', '2023-10-15 19:05:23', 2, 1),
    ('Особенно порадовал второй гол, красота!', '2023-10-15 20:12:45', 2, 1),
    ('Когда следующий матч? Не могу дождаться!', '2023-10-16 09:30:11', 1, 1);

-- Добавление тестовых комментариев ко второй новости (id=2)
INSERT INTO fc_comment (content, publication_date, author_id, news_id)
VALUES
    ('Добро пожаловать в команду, Иван!', '2023-10-10 12:30:05', 1, 2),
    ('Какой у него игровой номер?', '2023-10-10 13:45:22', 1, 2),
    ('Надеюсь, он усилит нашу атаку', '2023-10-11 08:15:33', 2, 2);

-- Добавление тестовых комментариев к третьей новости (id=3)
INSERT INTO fc_comment (content, publication_date, author_id, news_id)
VALUES
    ('Ждем новых побед в следующем сезоне!', '2023-09-28 15:20:01', 2, 3),
    ('Какие планы на предсезонные сборы?', '2023-09-29 10:45:12', 1, 3),
    ('Главное - без травм подготовиться', '2023-09-30 11:30:45', 2, 3);