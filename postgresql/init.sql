--
-- PostgreSQL database dump
--

-- Dumped from database version 16.0
-- Dumped by pg_dump version 16.0

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: user_service; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE user_service WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Russian_Russia.1251';


ALTER DATABASE user_service OWNER TO postgres;

\connect user_service

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: users; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA users;


ALTER SCHEMA users OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: audits; Type: TABLE; Schema: users; Owner: postgres
--

CREATE TABLE users.audits (
    id uuid,
    dt_create timestamp(3) without time zone,
    uuid uuid,
    mail text,
    fio text,
    role text,
    text text,
    essence_type text,
    essence_id text
);


ALTER TABLE users.audits OWNER TO postgres;

--
-- Name: codes; Type: TABLE; Schema: users; Owner: postgres
--

CREATE TABLE users.codes (
    id uuid NOT NULL,
    code text,
    mail text,
    send_code boolean
);


ALTER TABLE users.codes OWNER TO postgres;

--
-- Name: flats; Type: TABLE; Schema: users; Owner: postgres
--

CREATE TABLE users.flats (
    uuid uuid,
    dt_create timestamp(3) without time zone,
    dt_update timestamp(3) without time zone,
    offer_type text,
    description text,
    bedrooms bigint,
    area bigint,
    price bigint,
    floor bigint,
    photo_urls text[],
    original_url text
);


ALTER TABLE users.flats OWNER TO postgres;

--
-- Name: reports; Type: TABLE; Schema: users; Owner: postgres
--

CREATE TABLE users.reports (
    id uuid,
    dt_create timestamp(3) without time zone,
    dt_update timestamp(3) without time zone,
    status text,
    type text,
    description text,
    user_id text,
    from_date date,
    to_date date
);


ALTER TABLE users.reports OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: users; Owner: postgres
--

CREATE TABLE users.users (
    id uuid,
    dt_create timestamp(0) without time zone,
    dt_update timestamp(0) without time zone,
    mail text,
    fio text,
    user_role text,
    user_status text,
    password text
);


ALTER TABLE users.users OWNER TO postgres;

INSERT INTO users.codes (id, code, mail, send_code) VALUES ('f929d818-a105-4900-a3a3-cc89ec348325', '6063', 'test@gmail.com', 'FALSE');

--
-- Name: codes codes_pkey; Type: CONSTRAINT; Schema: users; Owner: postgres
--

ALTER TABLE ONLY users.codes
    ADD CONSTRAINT codes_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

