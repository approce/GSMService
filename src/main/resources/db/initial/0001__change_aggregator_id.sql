USE gsm_service;

ALTER TABLE messages CHANGE aggregator_id aggregator_id VARCHAR(30) DEFAULT NULL;