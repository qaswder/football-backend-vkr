CREATE SEQUENCE seq_role
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647;

CREATE TABLE fc_role_user
(
    id          int NOT NULL DEFAULT nextval('seq_role'),
    role_name   VARCHAR(50),
    description VARCHAR(100),
    PRIMARY KEY (id)
);

CREATE SEQUENCE seq_user
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647;

CREATE TABLE fc_user
(
    id           int NOT NULL DEFAULT nextval('seq_user'),
    username     VARCHAR(50),
    login        VARCHAR(100),
    email        VARCHAR(100),
    password     VARCHAR(255),
    user_role_id int NOT NULL,
    created_at   TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at   TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY (id),
    CONSTRAINT fk_user_role FOREIGN KEY (user_role_id) REFERENCES fc_role_user (id) ON DELETE CASCADE
);