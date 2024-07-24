ALTER TABLE remedio ADD valid tinyint NOT NULL;
update remedio set valid = 1;