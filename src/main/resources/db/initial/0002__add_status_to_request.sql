USE gsm_service;

ALTER TABLE requests ADD status ENUM('AVAILABLE','EXECUTING');