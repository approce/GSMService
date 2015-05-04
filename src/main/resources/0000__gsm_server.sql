CREATE DATABASE IF NOT EXISTS gsm_service
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

CREATE TABLE sims (
  sim_id      INT     NOT NULL AUTO_INCREMENT,
  number      BIGINT  NOT NULL UNIQUE,
  provider_id INT     NOT NULL,
  PRIMARY KEY (sim_id),
  FOREIGN KEY (provider_id) REFERENCES providers (provider_id)
);

CREATE TABLE providers (
  provider_id       INT           NOT NULL      AUTO_INCREMENT,
  name              VARCHAR(30)   NOT NULL,
  get_number_ussd   VARCHAR(30)   DEFAULT NULL,
  description       VARCHAR(100)  DEFAULT NULL,
  PRIMARY KEY (provider_id)
);

CREATE TABLE sim_cells (
  sim_cell_id   VARCHAR(4)   NOT NULL UNIQUE,
  description   VARCHAR(100) DEFAULT NULL,
  provider_id   INT          DEFAULT NULL,
  PRIMARY KEY (sim_cell_id),
  FOREIGN KEY (provider_id) REFERENCES providers(provider_id)
);

CREATE TABLE messages (
  message_id    BIGINT       NOT NULL AUTO_INCREMENT,
  aggregator_id INT          DEFAULT NULL,
  originator    VARCHAR(100) NOT NULL,
  body          VARCHAR(500) DEFAULT NULL,
  receipt_date  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (message_id)
);

CREATE TABLE requests (
  request_id    BIGINT    NOT NULL    AUTO_INCREMENT,
  date_create   TIMESTAMP NOT NULL    DEFAULT CURRENT_TIMESTAMP,
  date_finish   TIMESTAMP DEFAULT NULL,
  PRIMARY KEY (request_id)
);