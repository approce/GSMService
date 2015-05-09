USE gsm_service;

ALTER TABLE sim_cells CHANGE provider_id provider_id INT NOT NULL;
ALTER TABLE providers CHANGE get_number_ussd get_number_ussd VARCHAR(30) NOT NULL;