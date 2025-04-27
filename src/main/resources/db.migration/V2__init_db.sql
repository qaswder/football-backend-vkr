-- Создание таблицы команд
CREATE TABLE fc_team
(
    id              INT          NOT NULL DEFAULT nextval('seq_team'),
    team_name       VARCHAR(100) NOT NULL,
    team_short_name VARCHAR(10),
    stadium         VARCHAR(100),
    logo_url        VARCHAR(255),
    PRIMARY KEY (id)
);

-- Создание таблицы турниров
CREATE TABLE fc_tournament
(
    id              INT          NOT NULL DEFAULT nextval('seq_tournament'),
    tournament_name VARCHAR(100) NOT NULL,
    season          VARCHAR(20)  NOT NULL,
    PRIMARY KEY (id)
);

-- Создание таблицы ролей пользователей
CREATE TABLE fc_role_user
(
    id          INT         NOT NULL DEFAULT nextval('seq_role'),
    role_name   VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255) UNIQUE,
    PRIMARY KEY (id)
);

-- Создание таблицы пользователей
CREATE TABLE fc_user
(
    id           INT          NOT NULL DEFAULT nextval('seq_user'),
    username     VARCHAR(50)  NOT NULL UNIQUE,
    email        VARCHAR(100) NOT NULL UNIQUE,
    password     VARCHAR(255) NOT NULL,
    user_role_id INTEGER      NOT NULL,
    created_at   TIMESTAMP    NOT NULL,
    updated_at   TIMESTAMP    NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_user_role FOREIGN KEY (user_role_id) REFERENCES fc_role_user (id) ON DELETE CASCADE
);

-- Создание таблицы матчей
CREATE TABLE fc_match
(
    id            INT         NOT NULL DEFAULT nextval('seq_match'),
    home_team_id  INTEGER     NOT NULL,
    away_team_id  INTEGER     NOT NULL,
    score         VARCHAR(10),
    location      VARCHAR(100),
    match_status  VARCHAR(20) NOT NULL,
    date_time     TIMESTAMP   NOT NULL,
    tournament_id INTEGER     NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_match_home_team FOREIGN KEY (home_team_id) REFERENCES fc_team (id) ON DELETE CASCADE,
    CONSTRAINT fk_match_away_team FOREIGN KEY (away_team_id) REFERENCES fc_team (id) ON DELETE CASCADE,
    CONSTRAINT fk_tournament FOREIGN KEY (tournament_id) REFERENCES fc_tournament (id) ON DELETE CASCADE
);

-- Создание таблицы игроков
CREATE TABLE fc_player
(
    id            INT         NOT NULL DEFAULT nextval('seq_player'),
    surname       VARCHAR(50) NOT NULL,
    name          VARCHAR(50) NOT NULL,
    patronymic    VARCHAR(50),
    birth_date    DATE        NOT NULL,
    nationality   VARCHAR(50),
    player_number INTEGER,
    position      VARCHAR(20)     NOT NULL,
    photo_url     VARCHAR(255),
    team_id       INTEGER,
    PRIMARY KEY (id),
    CONSTRAINT fk_team FOREIGN KEY (team_id) REFERENCES fc_team (id) ON DELETE CASCADE
);

-- Создание таблицы персонала
CREATE TABLE fc_staff
(
    id         INT         NOT NULL DEFAULT nextval('seq_staff'),
    surname    VARCHAR(50) NOT NULL,
    name       VARCHAR(50) NOT NULL,
    patronymic VARCHAR(50),
    birthdate  DATE        NOT NULL,
    staff_role VARCHAR(20)     NOT NULL,
    photo_url  VARCHAR(255),
    team_id    INTEGER     NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_team FOREIGN KEY (team_id) REFERENCES fc_team (id) ON DELETE CASCADE
);

-- Создание таблицы результатов турниров
CREATE TABLE fc_results
(
    id            INT     NOT NULL DEFAULT nextval('seq_results'),
    position      INTEGER NOT NULL,
    played        INTEGER NOT NULL,
    wins          INTEGER NOT NULL,
    draws         INTEGER NOT NULL,
    losses        INTEGER NOT NULL,
    points        INTEGER NOT NULL,
    team_id       INTEGER NOT NULL,
    tournament_id INTEGER NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_team FOREIGN KEY (team_id) REFERENCES fc_team (id) ON DELETE CASCADE,
    CONSTRAINT fk_tournament FOREIGN KEY (tournament_id) REFERENCES fc_tournament (id) ON DELETE CASCADE,
    CONSTRAINT unique_team_tournament UNIQUE (team_id, tournament_id)
);

-- Создание таблицы статистики игроков
CREATE TABLE fc_player_statistics
(
    id             INT     NOT NULL DEFAULT nextval('seq_statistics'),
    player_id      INTEGER NOT NULL REFERENCES fc_player (id),
    match_id       INTEGER NOT NULL REFERENCES fc_match (id),
    goals          INTEGER NOT NULL DEFAULT 0,
    assists        INTEGER NOT NULL DEFAULT 0,
    yellow_cards   INTEGER NOT NULL DEFAULT 0,
    red_cards      INTEGER NOT NULL DEFAULT 0,
    played_minutes INTEGER NOT NULL DEFAULT 0,
    shots          INTEGER NOT NULL DEFAULT 0,
    passes         INTEGER NOT NULL DEFAULT 0,
    season         DATE    NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_player FOREIGN KEY (player_id) REFERENCES fc_player (id) ON DELETE CASCADE,
    CONSTRAINT fk_match FOREIGN KEY (match_id) REFERENCES fc_match (id) ON DELETE CASCADE,
    CONSTRAINT unique_player_match UNIQUE (player_id, match_id)
);

-- Создание таблицы новостей
CREATE TABLE fc_news
(
    id               INT           NOT NULL DEFAULT nextval('seq_news'),
    title            VARCHAR(255)  NOT NULL,
    content          TEXT          NOT NULL,
    publication_date TIMESTAMP     NOT NULL,
    image_url        VARCHAR(255),
    author_id        INTEGER       NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_news_author FOREIGN KEY (author_id) REFERENCES fc_user (id) ON DELETE CASCADE
);

-- Создание таблицы комментариев
CREATE TABLE fc_comment
(
    id               INT           NOT NULL DEFAULT nextval('seq_comment'),
    content          TEXT          NOT NULL,
    publication_date TIMESTAMP     NOT NULL,
    author_id        INTEGER       NOT NULL,
    news_id          INTEGER       NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_comment_author FOREIGN KEY (author_id) REFERENCES fc_user (id) ON DELETE CASCADE,
    CONSTRAINT fk_comment_news FOREIGN KEY (news_id) REFERENCES fc_news (id) ON DELETE CASCADE
);