USE gsm_service;

INSERT INTO providers (provider_id, name, get_number_ussd, description)
VALUES (1, 'kyivstar', '*161#', 'ukrainian kyivstart provider');

INSERT INTO sim_cells (sim_cell_id, description, provider_id)
VALUES ('A0', 'sim cell with empty messages', 1);

