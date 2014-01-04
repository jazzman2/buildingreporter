BEGIN;

INSERT INTO munit(id,name,name_en,symbol,multiple) VALUES(10,'Stupeň celziový', 'Celsius degree','°C',0);
INSERT INTO munit(id,name,name_en,symbol,multiple) VALUES(11,'Kelvin', 'Kelvin degree','°T',0);
INSERT INTO munit(id,name,name_en,symbol,multiple) VALUES(12,'Stupeň fahrenheita', 'Fahrenheit degree','°F',0);

INSERT INTO btype(id, name) VALUES(-1, 'Undefined building type');
INSERT INTO btype(id, name) VALUES(1, 'Building');
INSERT INTO btype(id, name) VALUES(2, 'Pipe');
INSERT INTO btype(id, name) VALUES(3, 'Heater');
INSERT INTO btype(id, name) VALUES(4, 'Outside');
INSERT INTO btype(id, name) VALUES(5, 'Room');
INSERT INTO btype(id, name) VALUES(6, 'Engine');

INSERT INTO bpart(id,name,type) VALUES (-1, 'Undefined',-1);

INSERT INTO bpart(id,name, type)VALUES (1, 'Blok A',1);
INSERT INTO bpart(id,name,type, parent) VALUES (2, 'Kotolna',5,1);
INSERT INTO bpart(id,name,type, parent) VALUES (3, 'Kotol 1',6,2);
INSERT INTO bpart(id,name,type, parent) VALUES (4, 'Kotol Vstup',2,3);
INSERT INTO bpart(id,name,type, parent) VALUES (5, 'Kotol Vystup',2,3);

COMMIT;