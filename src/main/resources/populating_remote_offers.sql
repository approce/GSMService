USE gsm_service;

CREATE TABLE offers (
  offer_id   INT         NOT NULL AUTO_INCREMENT,
  short_name VARCHAR(12) NOT NULL,
  full_name  VARCHAR(50) NOT NULL,
  priority   FLOAT                DEFAULT 0,
  originator VARCHAR(30) NOT NULL,
  PRIMARY KEY (offer_id)
);

INSERT INTO offers (offer_id, short_name, full_name, priority, originator)
VALUES (1, 'vk', 'vkontakte', 3.0, 'VKcom');

INSERT INTO offers (offer_id, short_name, full_name, priority, originator)
VALUES (2, 'od', 'odnoklassniki', 3.0, 'OKru');

INSERT INTO offers (offer_id, short_name, full_name, priority, originator)
VALUES (3, 'google', 'google', 3.0, 'Google');