BEGIN;

INSERT INTO munit(id,name,name_en,symbol,multiple) VALUES(10,'Stupeň celziový', 'Celsius degree','°C',0);
INSERT INTO munit(id,name,name_en,symbol,multiple) VALUES(11,'Kelvin', 'Kelvin degree','°T',0);
INSERT INTO munit(id,name,name_en,symbol,multiple) VALUES(12,'Stupeň fahrenheita', 'Fahrenheit degree','°F',0);

INSERT INTO btype(id, name) VALUES(-1, 'Undefined building type');
INSERT INTO btype(id, name) VALUES(1, 'Building');
INSERT INTO btype(id, name) VALUES(2, 'Pipe');
INSERT INTO btype(id, name) VALUES(3, 'Heater');
INSERT INTO btype(id, name) VALUES(4, 'Outside');

INSERT INTO bpart(id,name,type) VALUES (-1, 'Undefined building part',-1);

COMMIT;