CREATE SEQUENCE nomenclature_list_seq START WITH 1 INCREMENT 1;
CREATE SEQUENCE product_list_seq START WITH 1 INCREMENT 1;
CREATE SEQUENCE product_type_seq START WITH 1 INCREMENT 1;
CREATE SEQUENCE log_events_seq START WITH 1 INCREMENT 1;
CREATE SEQUENCE person_data_entity_seq START WITH 1 INCREMENT 1;

CREATE TABLE product_type
(
    id BIGINT PRIMARY KEY DEFAULT nextval('product_type_seq'),
    product_type        VARCHAR (50) UNIQUE NOT NULL,
);

CREATE TABLE nomenclature_list
(
    id  BIGINT PRIMARY KEY DEFAULT nextval('nomenclature_list_seq'),
    product_name        VARCHAR(50) UNIQUE NOT NULL,
    availability        VARCHAR(10) NOT NULL,
    price               NUMERIC(100, 2) NOT NULL,
    describe            VARCHAR(50),
    price               NUMERIC(100, 2) NOT NULL,
    product_type        BIGINT    NOT NULL,
    FOREIGN KEY (product_type) REFERENCES product_type(id) ON DELETE CASCADE
);

CREATE TABLE product_list
(
    id  BIGINT PRIMARY KEY DEFAULT nextval('product_list_seq'),
    amount              BIGINT      NOT NULL,
    nomenclature_id     BIGINT      NOT NULL
);

CREATE TABLE log_events
(
    id BIGINT PRIMARY KEY DEFAULT nextval('log_events_seq'),
    event_time      TIMESTAMP,
    method_name     VARCHAR(100),
    class_name      VARCHAR(200),
    args            VARCHAR(200)
);

CREATE TABLE person_data_entity
(
    id           BIGINT PRIMARY KEY DEFAULT nextval('person_data_entity_seq'),
    first_name   VARCHAR(100),
    last_name    VARCHAR(100),
    email        VARCHAR(128) UNIQUE NOT NULL,
    password     VARCHAR(250)        NOT NULL,
);

CREATE TYPE user_role AS ENUM ('ROLE_OWNER', 'ROLE_ADMIN');
ALTER TABLE user_entity ADD COLUMN role user_role DEFAULT 'ROLE_USER' ;