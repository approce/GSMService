CREATE DATABASE IF NOT EXISTS gsm_service
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

USE gsm_service;

CREATE TABLE providers (
  provider_id     INT         NOT NULL      AUTO_INCREMENT,
  name            VARCHAR(30) NOT NULL,
  get_number_ussd VARCHAR(30)               DEFAULT NULL,
  description     VARCHAR(100)              DEFAULT NULL,
  PRIMARY KEY (provider_id)
);

CREATE TABLE sims (
  sim_number  BIGINT NOT NULL UNIQUE,
  provider_id INT    NOT NULL,
  PRIMARY KEY (sim_number),
  FOREIGN KEY (provider_id) REFERENCES providers (provider_id)
);


CREATE TABLE sim_cells (
  sim_cell_id VARCHAR(4) NOT NULL UNIQUE,
  provider_id INT        NOT NULL,
  description VARCHAR(100) DEFAULT NULL,
  PRIMARY KEY (sim_cell_id),
  FOREIGN KEY (provider_id) REFERENCES providers (provider_id)
);

CREATE TABLE offers (
  offer_id   VARCHAR(10) NOT NULL,
  originator VARCHAR(30) NOT NULL,
  priority   DOUBLE,
  PRIMARY KEY (offer_id)
);

CREATE TABLE requests (
  request_id    BIGINT      NOT NULL    AUTO_INCREMENT,
  aggregator_id VARCHAR(30)             DEFAULT NULL,
  status        ENUM('AVAILABLE', 'EXECUTING'),
  offer_id      VARCHAR(10) NOT NULL,
  create_date   TIMESTAMP   NOT NULL    DEFAULT CURRENT_TIMESTAMP,
  start_date    TIMESTAMP,
  finish_date   TIMESTAMP,
  PRIMARY KEY (request_id),
  FOREIGN KEY (offer_id) REFERENCES offers (offer_id)
);

CREATE TABLE messages (
  message_id    BIGINT       NOT NULL AUTO_INCREMENT,
  aggregator_id VARCHAR(30)           DEFAULT NULL,
  offer_id      VARCHAR(10)  NOT NULL,
  sim_number    BIGINT       NOT NULL,
  request_id    BIGINT                DEFAULT NULL,
  originator    VARCHAR(100) NOT NULL,
  body          VARCHAR(500)          DEFAULT NULL,
  receipt_date  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (message_id),
  FOREIGN KEY (request_id) REFERENCES requests (request_id),
  FOREIGN KEY (sim_number) REFERENCES sims (sim_number),
  FOREIGN KEY (offer_id) REFERENCES offers (offer_id)

);

INSERT INTO providers (provider_id, name, get_number_ussd, description)
VALUES (1, 'kyivstar', '*161#', 'ukrainian kyivstart provider');

INSERT INTO sim_cells (sim_cell_id, description, provider_id)
VALUES ('A0', 'sim cell with clear sims', 1);





