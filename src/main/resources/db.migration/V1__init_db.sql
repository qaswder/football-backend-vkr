CREATE SEQUENCE seq_user
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647;

CREATE TABLE fc_user
(
    id         int NOT NULL DEFAULT nextval('seq_user'),
    username   VARCHAR(50),
    login      VARCHAR(100),
    email      VARCHAR(100),
    password   VARCHAR(255),
    user_role  VARCHAR(20),
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY (id)
);
