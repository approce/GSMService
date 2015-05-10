USE gsm_service;

ALTER TABLE requests ADD aggregator_id VARCHAR(30) DEFAULT NULL;

ALTER TABLE requests ADD start_date TIMESTAMP;