SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

CREATE DATABASE brmi WITH OWNER br;

\c brmi


CREATE TABLE mlog (
    id bigint NOT NULL PRIMARY KEY,
    log_date timestamp without time zone NOT NULL,
    value_measured double precision NOT NULL,
    value_transformed double precision,
    instrument_name VARCHAR(50),
	unit_measured VARCHAR(50),
	unit_transformed VARCHAR(50),
	sensor_id VARCHAR(50) 
);

ALTER TABLE public.mlog OWNER TO br;
