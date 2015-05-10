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
  description VARCHAR(100) DEFAULT NULL,
  provider_id INT          DEFAULT NULL,
  PRIMARY KEY (sim_cell_id),
  FOREIGN KEY (provider_id) REFERENCES providers (provider_id)
);

CREATE TABLE services (
  service_id INT         NOT NULL AUTO_INCREMENT,
  short_name VARCHAR(10) NOT NULL,
  full_name  VARCHAR(50) NOT NULL,
  PRIMARY KEY (service_id)
);

CREATE TABLE requests (
  request_id  BIGINT      NOT NULL    AUTO_INCREMENT,
  offer       VARCHAR(12) NOT NULL,
  create_date TIMESTAMP   NOT NULL    DEFAULT CURRENT_TIMESTAMP,
  finish_date TIMESTAMP,
  PRIMARY KEY (request_id)
);

CREATE TABLE messages (
  message_id    BIGINT       NOT NULL AUTO_INCREMENT,
  aggregator_id INT                   DEFAULT NULL,
  request_id    BIGINT                DEFAULT NULL,
  originator    VARCHAR(100) NOT NULL,
  body          VARCHAR(500)          DEFAULT NULL,
  receipt_date  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (message_id),
  FOREIGN KEY (request_id) REFERENCES requests (request_id)
);



