CREATE SEQUENCE nomenclature_seq START WITH 1 INCREMENT 1;
CREATE SEQUENCE product_seq START WITH 1 INCREMENT 1;
CREATE SEQUENCE product_type_seq START WITH 1 INCREMENT 1;
CREATE SEQUENCE log_seq START WITH 1 INCREMENT 1;
CREATE SEQUENCE person_data_seq START WITH 1 INCREMENT 1;

CREATE TABLE product_type
(
    id BIGINT PRIMARY KEY DEFAULT nextval('product__type_seq'),
    product_type        VARCHAR (50) UNIQUE NOT NULL,
);

CREATE TABLE nomenclature
(
    id  BIGINT PRIMARY KEY DEFAULT nextval('nomenclature_seq'),
    product_name        VARCHAR(50) UNIQUE NOT NULL,
    availability        VARCHAR(10) NOT NULL,
    price               NUMERIC(100, 2) NOT NULL,
    describe            VARCHAR(50),
    product_type        BIGINT    NOT NULL,
    FOREIGN KEY (product_type) REFERENCES product_type(id) ON DELETE CASCADE
);

CREATE TABLE product
(
    id  BIGINT PRIMARY KEY DEFAULT nextval('product_seq'),
    amount              BIGINT      NOT NULL,
    nomenclature_id     BIGINT      NOT NULL
);

CREATE TABLE log
(
    id BIGINT PRIMARY KEY DEFAULT nextval('log_seq'),
    time            TIMESTAMP,
    method_name     VARCHAR(100),
    class_name      VARCHAR(200),
    args            VARCHAR(200)
);

CREATE TABLE person_data
(
    id           BIGINT PRIMARY KEY DEFAULT nextval('person_data_seq'),
    first_name   VARCHAR(100),
    last_name    VARCHAR(100),
    email        VARCHAR(100) UNIQUE NOT NULL,
    password     VARCHAR(100)        NOT NULL,
);

CREATE TYPE user_role AS ENUM ('ROLE_OWNER', 'ROLE_ADMIN');
ALTER TABLE person_data ADD COLUMN role user_role;

CREATE TABLE shopping
(
    id           BIGINT PRIMARY KEY DEFAULT nextval('shopping_seq'),
    amount       BIGINT,
    type_id      BIGINT
    FOREIGN KEY (type_id) REFERENCES product_type(id) ON DELETE CASCADE
);

CREATE TABLE profit
(
    id           BIGINT PRIMARY KEY DEFAULT nextval('profit_seq'),
    amount       NUMERIC(100, 2),
    local_Date   TIMESTAMP
);
