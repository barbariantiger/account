DROP TABLE IF EXISTS account CASCADE;
DROP TABLE IF EXISTS customer CASCADE;
DROP TABLE IF EXISTS transaction CASCADE;

CREATE TABLE account (
    id VARCHAR(255) NOT NULL,
    balance NUMERIC(38,2),
    customer_id VARCHAR(255) NOT NULL,
    PRIMARY KEY (id));

CREATE TABLE customer (
    id VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    PRIMARY KEY(id));

CREATE TABLE transaction (
    id VARCHAR(255) NOT NULL,
    amount NUMERIC(38,2),
    created_on TIMESTAMP(6) NOT NULL,
    transaction_type SMALLINT,
    updated_balance NUMERIC(38,2),
    account_id VARCHAR(255) NOT NULL,
    PRIMARY KEY (id));

ALTER TABLE IF EXISTS transaction ADD CONSTRAINT account_fk FOREIGN KEY (account_id) REFERENCES account;
ALTER TABLE IF EXISTS account ADD CONSTRAINT customer_fk FOREIGN KEY (customer_id) REFERENCES customer;
